package service;

import dao.FileDao;
import dao.FileMeta;
import manager.FileManager;

import java.io.File;
import java.util.List;

// 通过这个 SearchService 来描述整个程序的核心业务逻辑.
public class SearchService {
    private FileDao fileDao = new FileDao();
    private FileManager fileManager = new FileManager();
    // 使用这个线程来周期性的扫描文件系统
    private Thread t = null;

    // 1. 提供一个初始化操作.
    public void init(String basePath) {
        // 初始情况下, 就把数据库初始化好, 并且进行一个初始的扫描操作.
        fileDao.initDB();
        // 把这个操作挪到 扫描线程 中完成了. 不在界面主线程中进行了
        // fileManager.scanAll(new File(basePath));
        t = new Thread(() -> {
            while (!t.isInterrupted()) {
                fileManager.scanAll(new File(basePath));
                try {
                    // Thread.sleep(60000);
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        t.start();
        System.out.println("[SearchService] 初始化完成!");
    }

    // 使用这个方法, 让扫描线程停止下来.
    public void shutdown() {
        if (t != null) {
            t.interrupt();
        }
    }

    // 2. 提供一个查找方法
    public List<FileMeta> search(String pattern) {
        return fileDao.searchByPattern(pattern);
    }
}
