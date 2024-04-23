import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisDemoList {
    public static void test1(Jedis jedis) {
        System.out.println("lpush 和 lrange");
        jedis.flushAll();

        jedis.lpush("key", "111", "222", "333");

        List<String> result = jedis.lrange("key", 0, -1);
        System.out.println(result);
    }

    public static void test2(Jedis jedis) {
        System.out.println("rpush");
        jedis.flushAll();

        jedis.rpush("key", "111", "222", "333");

        List<String> result = jedis.lrange("key", 0, -1);
        System.out.println("result: " + result);
    }

    public static void test3(Jedis jedis) {
        System.out.println("lpop");
        jedis.flushAll();

        jedis.rpush("key", "111", "222", "333");
        String result = jedis.lpop("key");
        System.out.println("result: " + result);

        result = jedis.lpop("key");
        System.out.println("result: " + result);

        result = jedis.lpop("key");
        System.out.println("result: " + result);

        result = jedis.lpop("key");
        System.out.println("result: " + result);
    }

    public static void test4(Jedis jedis) {
        System.out.println("rpop");
        jedis.flushAll();

        jedis.rpush("key", "111", "222", "333");
        String result = jedis.rpop("key");
        System.out.println("result: " + result);
        result = jedis.rpop("key");
        System.out.println("result: " + result);
        result = jedis.rpop("key");
        System.out.println("result: " + result);
        result = jedis.rpop("key");
        System.out.println("result: " + result);
    }

    public static void test5(Jedis jedis) {
        System.out.println("blpop");
        jedis.flushAll();

        // 返回结果是一个 "二元组", 一个是从哪个 key 对应的list中删除的, 一个是删除的元素是什么.
        List<String> results = jedis.blpop(100, "key");
        System.out.println("results[0]: " + results.get(0));
        System.out.println("results[1]: " + results.get(1));
    }

    public static void test6(Jedis jedis) {
        System.out.println("llen");
        jedis.flushAll();

        jedis.rpush("key", "111", "222", "333");
        long len = jedis.llen("key");
        System.out.println("len: " + len);
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try (Jedis jedis = jedisPool.getResource()) {
            // test1(jedis);
            // test2(jedis);
            // test3(jedis);
            // test4(jedis);
            // test5(jedis);
            test6(jedis);
        }
    }
}
