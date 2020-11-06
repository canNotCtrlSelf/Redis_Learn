package com.cncs.test;

import com.cncs.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis的使用
 */
public class JedisTest {

    /**
     * string 数据结构
     */
    @Test
    public void test1() {
        //创建连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作数据
        jedis.set("username", "zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
        //设置一个指定过期时间的key value
        jedis.setex("age", 20, "18");
        //关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构
     */
    @Test
    public void test2() {
        //创建连接
        Jedis jedis = new Jedis();
        //操作数据
        //存储数据
        jedis.hset("user", "name", "wangwu");
        jedis.hset("user", "age", "18");
        jedis.hset("user", "address", "guangxi");
        //获取数据
        //获取hashset的map中的数据
        Map<String, String> user = jedis.hgetAll("user");
        //keyset
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            System.out.println(key);
        }
        //关闭连接
        jedis.close();
    }

    /**
     * list 数据结构
     */
    @Test
    public void test3() {
        //创建连接
        Jedis jedis = new Jedis();
        //操作数据
        //存储数据
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");

        //获取数据
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //删除数据
        String popEle1 = jedis.lpop("mylist");
        String popEle2 = jedis.rpop("mylist");
        mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //关闭连接
        jedis.close();
    }

    /**
     * set 数据结构
     */
    @Test
    public void test4() {
        //创建连接
        Jedis jedis = new Jedis();
        //操作数据
        //存储数据
        jedis.sadd("myset", "1", "2", "3");
        //获取数据
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        //关闭连接
        jedis.close();
    }

    /**
     * sortedset 数据结构
     */
    @Test
    public void test5() {
        //创建连接
        Jedis jedis = new Jedis();
        //操作数据
        //存储数据
        jedis.zadd("mysortedset", 10, "奇瑞qq");
        jedis.zadd("mysortedset", 100, "劳斯莱斯");
        jedis.zadd("mysortedset", 50, "别克");

        //获取数据
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        //关闭连接
        jedis.close();
    }

    /**
     * jedis连接池的使用
     */
    @Test
    public void test6() {
        //获取连接
        Jedis jedis = JedisPoolUtils.getJedis();
        //操作
        jedis.set("music","take me higher");
        //获取数据
        String music = jedis.get("music");
        System.out.println(music);
        //关闭连接
        JedisPoolUtils.close();
    }
}
