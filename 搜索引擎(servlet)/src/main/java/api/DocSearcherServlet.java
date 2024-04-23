package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import searcher.DocSearcher;
import searcher.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 这个注解就是在指定当前的路径和哪个 Servlet 类对应!
@WebServlet("/searcher")
public class DocSearcherServlet extends HttpServlet {
    // 此处的 searcher.DocSearcher 对象也应该是全局唯一的. 因此就给一个 static 修饰.
    private static DocSearcher docSearcher = new DocSearcher();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 先解析请求, 拿到用户提交的查询词.
        String query = req.getParameter("query");
        if (query == null || query.equals("")) {
            String msg = "您的参数非法! 没有获取到 query 的值!";
            System.out.println(msg);
            resp.sendError(404, msg);
            return;
        }
        // 2. 打印记录一下 query 的值
        System.out.println("query=" + query);
        // 3. 调用搜索模块, 来进行搜索.
        List<Result> results = docSearcher.search(query);
        // 4. 把当前的搜索结果进行打包.
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(), results);
    }
}
