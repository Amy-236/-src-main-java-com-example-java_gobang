package com.bit.java_doc_searcher.controller;

import com.bit.java_doc_searcher.searcher.DocSearcher;
import com.bit.java_doc_searcher.searcher.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocSearcherController {
    private static DocSearcher searcher = new DocSearcher();
    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/searcher", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String search(@RequestParam("query") String query) throws JsonProcessingException {
        // 参数是查询词, 返回值是响应内容.
        // 参数 query 来自于请求 URL 的 query string 中的 query 这个 key 的值
        List<Result> results = searcher.search(query);
        return objectMapper.writeValueAsString(results);
    }
}
