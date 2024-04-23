import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisDemoString {

    public static void test1(Jedis jedis) {
        System.out.println("mget 和 mset");
        jedis.flushAll();

        jedis.mset("key1", "111", "key2", "222", "key3", "333");

        List<String> values = jedis.mget("key1", "key2", "key100", "key3");
        System.out.println("values: " + values);
    }

    public static void test2(Jedis jedis) {
        System.out.println("getrange 和 setrange");
        jedis.flushAll();

        jedis.set("key", "abcdefghijk");
        String result = jedis.getrange("key", 2, 5);
        System.out.println("result: " + result);

        jedis.setrange("key", 2, "xyz");
        String value = jedis.get("key");
        System.out.println("value: " + value);
    }

    public static void test3(Jedis jedis) {
        System.out.println("append");
        jedis.flushAll();

        jedis.set("key", "abcdef");
        jedis.append("key", "ghij");

        String value = jedis.get("key");
        System.out.println("value: " + value);
    }

    public static void test4(Jedis jedis) {
        System.out.println("incr 和 decr");
        jedis.flushAll();

        jedis.set("key", "100");

        long result = jedis.incr("key");
        System.out.println("result: " + result);

        String value = jedis.get("key");
        System.out.println("value: " + value);

        result = jedis.decr("key");
        System.out.println("result: " + result);

        value = jedis.get("key");
        System.out.println("value: " + value);
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try (Jedis jedis = jedisPool.getResource()) {
            // test1(jedis);
            // test2(jedis);
            // test3(jedis);
            test4(jedis);
        }
    }
}
