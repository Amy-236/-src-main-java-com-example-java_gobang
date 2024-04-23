import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisDemoHash {
    public static void test1(Jedis jedis) {
        System.out.println("hset 和 hget");
        jedis.flushAll();

        jedis.hset("key", "f1", "111");
        Map<String, String> fields = new HashMap<>();
        fields.put("f2", "222");
        fields.put("f3", "333");
        jedis.hset("key", fields);

        String result = jedis.hget("key", "f1");
        System.out.println("result: " + result);

        result = jedis.hget("key", "f2");
        System.out.println("result: " + result);

        result = jedis.hget("key", "f100");
        System.out.println("result: " + result);
    }

    public static void test2(Jedis jedis) {
        System.out.println("hexists");
        jedis.flushAll();

        jedis.hset("key", "f1", "111");
        jedis.hset("key", "f2", "111");
        jedis.hset("key", "f3", "111");

        boolean result = jedis.hexists("key", "f1");
        System.out.println("result: " + result);

        result = jedis.hexists("key", "f100");
        System.out.println("result: " + result);
    }

    public static void test3(Jedis jedis) {
        System.out.println("hdel");
        jedis.flushAll();

        jedis.hset("key", "f1", "111");
        jedis.hset("key", "f2", "111");
        jedis.hset("key", "f3", "111");

        long result = jedis.hdel("key", "f1", "f2");
        System.out.println("result: " + result);

        boolean exists = jedis.hexists("key", "f1");
        System.out.println("exists: " + exists);
        exists = jedis.hexists("key", "f2");
        System.out.println("exists: " + exists);
    }

    public static void test4(Jedis jedis) {
        System.out.println("hkeys 和 hvals");
        jedis.flushAll();

        jedis.hset("key", "f1", "111");
        jedis.hset("key", "f2", "111");
        jedis.hset("key", "f3", "111");

        Set<String> fields = jedis.hkeys("key");
        List<String> vals = jedis.hvals("key");
        System.out.println("fields: " + fields);
        System.out.println("vals: " + vals);
    }

    public static void test5(Jedis jedis) {
        System.out.println("hmget 和 hmset");
        jedis.flushAll();

        Map<String, String> map = new HashMap<>();
        map.put("f1", "111");
        map.put("f2", "222");
        map.put("f3", "333");
        jedis.hmset("key", map);

        List<String> values = jedis.hmget("key", "f2", "f1", "f3");
        System.out.println("values: " + values);
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try (Jedis jedis = jedisPool.getResource()) {
            // test1(jedis);
            // test2(jedis);
            // test3(jedis);
            // test4(jedis);
            test5(jedis);
        }
    }
}
