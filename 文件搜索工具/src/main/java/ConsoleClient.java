import dao.FileMeta;
import service.SearchService;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleClient {
    public static void main(String[] args) {
        // 先让用户输入一个扫描的文件路径
        // 然后再让用户输入一个具体要查询的词.
        // 根据这个词来进行展开搜索.
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的路径: ");
        String basePath = scanner.next();
        // 针对该路径进行初始化
        SearchService searchService = new SearchService();
        searchService.init(basePath);
        // 创建一个主循环, 反复的读取数据, 并进行搜索功能
        while (true) {
            System.out.println("请输入要搜索的关键词: ");
            String word = scanner.next();
            List<FileMeta> fileMetaList = searchService.search(word);
            System.out.println("====================================");
            for (FileMeta fileMeta : fileMetaList) {
                System.out.println(fileMeta.getPath() + File.separator + fileMeta.getName());
            }
            System.out.println("====================================");
        }
    }
}
