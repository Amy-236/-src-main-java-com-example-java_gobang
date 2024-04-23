import java.io.File;

public class TestURL {
    private static final String INPUT_PATH = "D:/doc_searcher_index/jdk-8u231-docs-all/docs/api/";

    public static void main(String[] args) {
        File file = new File("D:\\doc_searcher_index\\jdk-8u231-docs-all\\docs\\api\\java\\util\\ArrayList.html");
        // 先获取到一个固定的前缀
        String part1 = "https://docs.oracle.com/javase/8/docs/api/";
        String part2 = file.getAbsolutePath().substring(INPUT_PATH.length());
        String result = part1 + part2;
        System.out.println(result);

    }
}
