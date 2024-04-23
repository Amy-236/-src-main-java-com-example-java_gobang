package dao;

import util.PinyinUtil;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

// 这个类的实例就代表 file_meta 表里的每个记录.
public class FileMeta {
    private int id;
    private String name;
    private String path;
    private boolean isDirectory;
    // 下面两个属性, 在 java 代码中, 可以没有.
    // 都是根据上述的 name 属性来的.
    // 而是可以直接给这个类加两个 get 方法来获取到 pinyin 和 pinyinFirst
//    private String pinyin;
//    private String pinyinFirst;
    // size 单位是字节. 最终给界面上显示的内容, 则不应该以字节为单位.
    private long size;
//    private String sizeText;
    // last_modified 是时间戳. 这也是一个非常大的数字!!
    // 直接显示在界面上也不好看, 而要进行格式化转换
    private long lastModified;
//    private String lastModifiedText;

    public String getPinyin() {
        return PinyinUtil.get(name, true);
    }

    public String getPinyinFirst() {
        return PinyinUtil.get(name, false);
    }

    public String getSizeText() {
        // 通过这个方法, 把 size 的值进行合理的单位换算, 变成更易读的结果
        // 单位主要就是四个. Byte, KB, MB, GB.
        // 由于单个文件不太可能达到 TB 级别, 只考虑这四个单位就差不多了.
        // 主要是看 size 的大小.
        // 如果 size 是 < 1024 , 单位直接使用 Byte 即可.
        // 如果 size 是 >= 1024 并且 < 1024 * 1024, 单位使用 MB
        // 假设 size 是 1025, 此时显示的结果, 肯定不是 1025 MB..... 1.001 MB
        // .....

        // 这里的关键思路:
        // 1. 比较 size 和 1024 之间的大小关系.
        // 2. 如果 size < 1024 , 直接获取到结果; 如果 size >= 1024, 则除等与 1024, 并且单位升级一下
        double curSize = size;
        String[] units = {"Byte", "KB", "MB", "GB"};
        for (int level = 0; level < units.length; level++) {
            if (curSize < 1024) {
                // 不必继续换算了, 直接使用当前 level 对应的单位即可!
                return String.format("%.2f " + units[level], new BigDecimal(curSize));
            }
            curSize /= 1024;
        }
        // 当单位升级到 GB 还不够用, 还是就以 GB 为单位就得了.
        return String.format("%.2f GB", new BigDecimal(curSize));
    }

    public String getLastModifiedText() {
        // 关于这里的格式化时间的字符串, 千万不要去背!!! 背容易背错!!! 一定要用的时候就去查一下.
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(lastModified);
    }

    public FileMeta(String name, String path, boolean isDirectory, long size, long lastModified) {
        this.name = name;
        this.path = path;
        this.isDirectory = isDirectory;
        this.size = size;
        this.lastModified = lastModified;
    }

    public FileMeta(File f) {
        this(f.getName(), f.getParent(), f.isDirectory(), f.length(), f.lastModified());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            // 看看是不是自己和自己比较
            return true;
        }
        if (o == null) {
            // 针对 o 为 null 的特殊处理
            return false;
        }
        if (getClass() != o.getClass()) {
            // 比较类型
            return false;
        }
        FileMeta fileMeta = (FileMeta)o;
        return name.equals(fileMeta.name) && path.equals(fileMeta.path) && isDirectory == fileMeta.isDirectory;
    }

    // 当前是已经重写了 equals 了. 很多 Java 相关的编程规范, 都建议咱们也重写下 hashCode.
    // 因为两个对象如果 equals 为 true, 此时他们得到的 hashCode 也应该是相同的.
    // 但是此处代码中, 不写 hashCode 也不是不行. 毕竟当前不需要把这个 FileMeta 放到 哈希表 里.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
