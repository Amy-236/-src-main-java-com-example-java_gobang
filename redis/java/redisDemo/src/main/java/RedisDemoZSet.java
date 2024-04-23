import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisDemoZSet {

    public static void test1(Jedis jedis) {
        System.out.println("zadd 和 zrange");
        jedis.flushAll();

        jedis.zadd("key", 10, "zhangsan");
        Map<String, Double> map = new HashMap<>();
        map.put("lisi", 20.0);
        map.put("wangwu", 30.0);
        jedis.zadd("key", map);

        List<String> members = jedis.zrange("key", 0, -1);
        System.out.println("members: " + members);

        List<Tuple> membersWithScore = jedis.zrangeWithScores("key", 0, -1);
        System.out.println("membersWithScore: " + membersWithScore);
        String member = membersWithScore.get(0).getElement();
        double score = membersWithScore.get(0).getScore();
        System.out.println("member: " + member + ", score: " + score);
    }

    public static void test2(Jedis jedis) {
        System.out.println("zcard");
        jedis.flushAll();

        jedis.zadd("key", 10, "zhangsan");
        jedis.zadd("key", 20, "lisi");
        jedis.zadd("key", 30, "wangwu");

        long len = jedis.zcard("key");
        System.out.println("len: " + len);
    }

    public static void test3(Jedis jedis) {
        System.out.println("zrem");
        jedis.flushAll();

        jedis.zadd("key", 10, "zhangsan");
        jedis.zadd("key", 20, "lisi");
        jedis.zadd("key", 30, "wangwu");

        long n = jedis.zrem("key", "zhangsan", "lisi");
        System.out.println("n: " + n);

        List<Tuple> result = jedis.zrangeWithScores("key", 0, -1);
        System.out.println("result: " + result);
    }

    public static void test4(Jedis jedis) {
        System.out.println("zscore");
        jedis.flushAll();

        jedis.zadd("key", 10, "zhangsan");
        jedis.zadd("key", 20, "lisi");
        jedis.zadd("key", 30, "wangwu");

        Double score = jedis.zscore("key", "zhangsan2");
        System.out.println("score: " + score);
    }

    public static void test5(Jedis jedis) {
        System.out.println("zrank");
        jedis.flushAll();

        jedis.zadd("key", 10, "zhangsan");
        jedis.zadd("key", 20, "lisi");
        jedis.zadd("key", 30, "wangwu");

        Long rank = jedis.zrank("key", "zhangsan2");
        System.out.println("rank: " + rank);
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
