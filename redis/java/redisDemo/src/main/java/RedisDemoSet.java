import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class RedisDemoSet {

    public static void test1(Jedis jedis) {
        System.out.println("sadd 和 smembers");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333");
        Set<String> result = jedis.smembers("key");
        System.out.println("result: " + result);
    }

    public static void test2(Jedis jedis) {
        System.out.println("sismember");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333");
        boolean result = jedis.sismember("key", "100");
        System.out.println("result: " + result);
    }

    public static void test3(Jedis jedis) {
        System.out.println("scard");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333");
        long result = jedis.scard("key");
        System.out.println("result: " + result);
    }

    public static void test4(Jedis jedis) {
        System.out.println("spop");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333", "444", "555");
        String result = jedis.spop("key");
        System.out.println("result: " + result);
    }

    public static void test5(Jedis jedis) {
        System.out.println("sinter");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333");
        jedis.sadd("key2", "111", "222", "444");

        Set<String> result = jedis.sinter("key", "key2");
        System.out.println("result: " + result);
    }

    public static void test6(Jedis jedis) {
        System.out.println("sinterstore");
        jedis.flushAll();

        jedis.sadd("key", "111", "222", "333");
        jedis.sadd("key2", "111", "222", "444");

        long len = jedis.sinterstore("key3", "key", "key2");
        System.out.println("len: " + len);

        Set<String> result = jedis.smembers("key3");
        System.out.println("result: " + result);
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
