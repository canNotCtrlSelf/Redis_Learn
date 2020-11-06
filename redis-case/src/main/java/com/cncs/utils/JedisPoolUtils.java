package com.cncs.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.security.util.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JedisPool工具类
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //加载配置文件
        InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建properties对象
        Properties properties = new Properties();
        //关联文件
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取属性文件并配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        //初始化jedis连接池
        jedisPool = new JedisPool(config,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));
    }

    /**
     * 获取Jedis连接
     *
     * @return
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关联连接 归还连接到连接池
     */
    public static void close(){
        jedisPool.close();
    }

}
