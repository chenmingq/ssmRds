package com.mcin.test;

import com.mcin.util.SerializeUti;
import com.mcin.util.ToUtil;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mcin on 2017/3/9.
 */
public class RedisTest {
    //连接redis服务
    static Jedis jedis = new Jedis("localhost",6379);

}
