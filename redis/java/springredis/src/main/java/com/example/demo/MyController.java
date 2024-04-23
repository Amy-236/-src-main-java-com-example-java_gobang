package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

// 后续 redis 测试的各种方法, 都通过这个 Controller 提供的 http 接口来触发.
@RestController
public class MyController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/testString")
    @ResponseBody
    public String testString() {
        redisTemplate.execute((RedisConnection connection) -> {
            // execute 要求回调方法中必须写 return 语句. 返回个东西.
            // 这个回调返回的对象, 就会作为 execute 本身的返回值.
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForValue().set("key", "111");
        redisTemplate.opsForValue().set("key2", "222");
        redisTemplate.opsForValue().set("key3", "333");

        String value = redisTemplate.opsForValue().get("key");
        System.out.println("value: " + value);

        return "OK";
    }

    @GetMapping("/testList")
    @ResponseBody
    public String testList() {
        // 先清除之前的数据.
        redisTemplate.execute((RedisConnection connection) -> {
            // execute 要求回调方法中必须写 return 语句. 返回个东西.
            // 这个回调返回的对象, 就会作为 execute 本身的返回值.
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForList().leftPush("key", "111");
        redisTemplate.opsForList().leftPush("key", "222");
        redisTemplate.opsForList().leftPush("key", "333");

        String value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: " + value);
        value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: " + value);
        value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: " + value);

        return "OK";
    }

    @GetMapping("/testSet")
    @ResponseBody
    public String testSet() {
        redisTemplate.execute((RedisConnection connection) -> {
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForSet().add("key", "111", "222", "333");
        Set<String> result = redisTemplate.opsForSet().members("key");
        System.out.println("result: " + result);

        Boolean exists = redisTemplate.opsForSet().isMember("key", "111");
        System.out.println("exists: " + exists);

        Long count = redisTemplate.opsForSet().size("key");
        System.out.println("count: " + count);

        redisTemplate.opsForSet().remove("key", "111", "222");
        result = redisTemplate.opsForSet().members("key");
        System.out.println("result: " + result);

        return "OK";
    }

    @GetMapping("/testHash")
    @ResponseBody
    public String testHash() {
        redisTemplate.execute((RedisConnection connection) -> {
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForHash().put("key", "f1", "111");
        redisTemplate.opsForHash().put("key", "f2", "222");
        redisTemplate.opsForHash().put("key", "f3", "333");

        String value = (String) redisTemplate.opsForHash().get("key", "f1");
        System.out.println("value: " + value);

        Boolean exists = redisTemplate.opsForHash().hasKey("key", "f1");
        System.out.println("exists: " + exists);

        redisTemplate.opsForHash().delete("key", "f1", "f2");

        Long size = redisTemplate.opsForHash().size("key");
        System.out.println("size: " + size);

        return "OK";
    }

    @GetMapping("/testZSet")
    @ResponseBody
    public String testZSet() {
        redisTemplate.execute((RedisConnection connection) -> {
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForZSet().add("key", "zhangsan", 10);
        redisTemplate.opsForZSet().add("key", "lisi", 20);
        redisTemplate.opsForZSet().add("key", "wangwu", 30);

        Set<String> members = redisTemplate.opsForZSet().range("key", 0, -1);
        System.out.println("members: " + members);

        Set<ZSetOperations.TypedTuple<String>> membersWithScore = redisTemplate.opsForZSet().rangeWithScores("key", 0, -1);
        System.out.println("membersWithScore: " + membersWithScore);

        Double score = redisTemplate.opsForZSet().score("key", "zhangsan");
        System.out.println("score: " + score);

        redisTemplate.opsForZSet().remove("key", "zhangsan");

        Long size = redisTemplate.opsForZSet().size("key");
        System.out.println("size: " + size);

        Long rank = redisTemplate.opsForZSet().rank("key", "lisi");
        System.out.println("rank: " + rank);

        return "OK";
    }
}
