import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

public class RedisDemoGeneric {
    public static void test1(Jedis jedis) {
        System.out.println("get 和 set 的使用");
        // 先清空数据库. 要避免上一组测试的残留数据影响到下一组测试的结果.
        jedis.flushAll();

        jedis.set("key", "111");
        jedis.set("key2", "222");
        SetParams params = new SetParams();
        params.ex(10);
        params.nx();
        jedis.set("key", "333", params);

        String value = jedis.get("key");
        System.out.println("value=" + value);
    }

    public static void test2(Jedis jedis) {
        System.out.println("exists 和 del");
        jedis.flushAll();

        jedis.set("key", "111");
        jedis.set("key2", "222");
        jedis.set("key3", "333");

        boolean result = jedis.exists("key");
        System.out.println("result: " + result);

        long result2 = jedis.del("key");
        System.out.println("result2: " + result2);

        result = jedis.exists("key");
        System.out.println("result: " + result);

        result2 = jedis.del("key", "key2", "key3");
        System.out.println("result2: " + result2);
    }

    public static void test3(Jedis jedis) {
        System.out.println("keys");
        jedis.flushAll();

        jedis.set("key", "111");
        jedis.set("key2", "111");
        jedis.set("key3", "111");
        jedis.set("key4", "111");

        // redis 中的 key 不能重复~ 而且也是不在意顺序的.
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
    }

    public static void test4(Jedis jedis) {
        System.out.println("expire 和 ttl");
        jedis.flushAll();

        jedis.set("key", "111");
        jedis.expire("key", 10);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = jedis.ttl("key");
        System.out.println("time: " + time);
    }

    public static void test5(Jedis jedis) {
        System.out.println("type");
        jedis.flushAll();

        jedis.set("key", "111");
        String type = jedis.type("key");
        System.out.println("type: " + type);

        jedis.lpush("key2", "111", "222", "333");
        type = jedis.type("key2");
        System.out.println("type: " + type);

        jedis.hset("key3", "f1", "111");
        type = jedis.type("key3");
        System.out.println("type: " + type);

        jedis.sadd("key4", "111", "222", "333");
        type = jedis.type("key4");
        System.out.println("type: " + type);

        jedis.zadd("key5", 10, "zhangsan");
        type = jedis.type("key5");
        System.out.println("type: " + type);
    }

    public static void main(String[] args) {
        // 连接到 Redis 服务器上.
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");

        try (Jedis jedis = jedisPool.getResource()) {
            // redis 的各种命令, 就都对应到 jedis 对象的各种方法.
//            String pong = jedis.ping();
//            System.out.println(pong);

            // test1(jedis);
            // test2(jedis);
            // test3(jedis);
            // test4(jedis);
            test5(jedis);
        }
    }
}
