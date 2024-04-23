import searcher.Parser;

import java.io.File;

public class TestParseContent {
    public static void main(String[] args) {
        Parser parser = new Parser();
        File file = new File("D:\\doc_searcher_index\\jdk-8u231-docs-all\\docs\\api\\java\\util\\ArrayList.html");
        String result = parser.parseContent(file);
        String result2 = parser.parseContentByRegex(file);
        System.out.println(result);
        System.out.println(result2);
    }
}
