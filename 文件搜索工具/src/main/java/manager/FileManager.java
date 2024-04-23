package manager;

import dao.FileDao;
import dao.FileMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// 针对目录进行扫描, 并把扫描的结果同步更新到数据库中.
public class FileManager {
    private FileDao fileDao = new FileDao();
    // 初始化设置选手数目为 1, 当线程池执行完所有任务之后, 就立即调用一次 countDown 撞线.
    private CountDownLatch countDownLatch = null;
    // 用来衡量任务结束的计数器.
    private AtomicInteger taskCount = new AtomicInteger(0);

    // 通过这个方法, 实现针对 basePath 描述的目录, 内容的扫描.
    // 把这里的文件和子目录都扫描清楚, 并且保存到数据库中.
    public void scanAll(File basePath) {
        System.out.println("[FileManager] scanAll 开始!");
        long beg = System.currentTimeMillis();

        // 保证每次调用 scanAll 都重新创建 CountDownLatch. 重新进行计数了.
        countDownLatch = new CountDownLatch(1);

        // scanAllByOneThread(basePath);
        scanAllByThreadPool(basePath);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("[FileManager] scanAll 结束! 执行时间: " + (end - beg) + " ms");
    }

    private void scanAllByOneThread(File basePath) {
        if (!basePath.isDirectory()) {
            return;
        }

        // 先针对当前目录进行扫描
        scan(basePath);

        // 列出当前目录下包含的所有文件
        File[] files = basePath.listFiles();
        if (files == null || files.length == 0) {
            // 当前目录下没东西.
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                scanAllByOneThread(f);
            }
        }
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(8);

    private void scanAllByThreadPool(File basePath) {
        if (!basePath.isDirectory()) {
            return;
        }

        // 计数器自增
        taskCount.getAndIncrement(); // taskCount++

        // 扫描操作, 放到线程池里完成.
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    scan(basePath);
                } finally {
                    // 计数器自减
                    // 把这个自减逻辑放到 finally 中, 确保自减操作是肯定能执行到的.
                    taskCount.getAndDecrement(); // taskCount--
                    if (taskCount.get() == 0) {
                        // 如果计数器为 0 了, 就通知主线程停表了.
                        countDownLatch.countDown();
                    }
                }
            }
        });

        // 继续递归其他目录.
        File[] files = basePath.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                scanAllByThreadPool(f);
            }
        }
    }

    // scan 方法针对一个目录进行处理. (整个遍历目录过程中的基本操作)
    // 这个方法只针对当前 path 对应的目录进行分析.
    // 列出这个 path 下包含的文件和子目录, 并且把这些内容更新到数据库中.
    // 此方法不考虑子目录里面的内容.
    private void scan(File path) {
        // DEBUG 这个日志打印的有点多, 先临时去掉.
        // System.out.println("[FileManager] 扫描路径: " + path.getAbsolutePath());
        // 1. 列出文件系统上真实的文件/目录
        List<FileMeta> scanned = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File f : files) {
                scanned.add(new FileMeta(f));
            }
        }

        // 2. 列出数据库里当前指定目录里面的内容
        List<FileMeta> saved = fileDao.searchByPath(path.getPath());

        // 3. 找出文件系统中没有的, 数据库中有的, 把这些内容从数据库删除掉
        List<FileMeta> forDelete = new ArrayList<>();
        for (FileMeta fileMeta : saved) {
            if (!scanned.contains(fileMeta)) {
                // 这个操作就是把数据库中有的, 文件系统中没有的, 拎出来了.
                forDelete.add(fileMeta);
            }
        }
        fileDao.delete(forDelete);

        // 4. 找出文件系统中有的, 数据库没有的, 把这些内容往数据库里插入.
        List<FileMeta> forAdd = new ArrayList<>();
        for (FileMeta fileMeta : scanned) {
            if (!saved.contains(fileMeta)) {
                // 找出了文件系统中有, 数据库没有的.
                forAdd.add(fileMeta);
            }
        }
        fileDao.add(forAdd);
    }
}
