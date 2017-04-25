package com.mcin.service;

import com.mcin.util.EMUtil;
import com.mcin.util.LogInfo;
import com.mcin.util.ToUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mcin on 2017/4/9.
 * 对redis操作的service
 */

@Service
public class RedisService {


    @Resource
    private JedisPool jedisPool;



    /**
     * 数据保存redis
     * @param object
     * @return
     */
    public Integer setCache(Object object,String str) throws JedisConnectionException {

        Integer result = 0;

        if (object != null){
            Map map = new HashMap();
            Jedis jedis = jedisPool.getResource();
            try {
                map = ToUtil.setModelName(object,str);
                Iterator it = map.entrySet().iterator();
                String key = "";
                String value = "";

                while (it.hasNext()){
                    Map.Entry entry = (Map.Entry)it.next();
                    if (entry.getValue() != null) {
                        key = entry.getKey().toString();
                        value = entry.getValue().toString();
                        jedis.set(key, value);
                    }
                }
                result = EMUtil.USER_INFO.INSERT_OK.KEY;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
            } catch (Exception e){
                e.printStackTrace();
                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
            } finally {
                ToUtil.closeRedis(jedis);
            }
        }
        return result;
    }


    /**
     *  查询出redis的数据
     * @return
     */
    public Map getCache(Object object,String str) throws JedisConnectionException {
        Map resuleMap = new HashMap();
        Map map = new HashMap();
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                map = ToUtil.setModelName(object,str);
                Set set = map.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> enter = (Map.Entry<String, Object>) iterator.next();
                    LogInfo.info(enter.getKey()+" -->> "+jedis.get(enter.getKey()));
                    if (jedis.get(enter.getKey()) != null) {
                        String beanKey = ToUtil.sub_str(enter.getKey());
                        resuleMap.put(beanKey,jedis.get(enter.getKey()));
                        LogInfo.info(enter.getKey()+"\t"+enter.getValue());
                    }
                }
            } catch (Exception e ) {
                e.printStackTrace();
                resuleMap = null;
            } finally {
                ToUtil.closeRedis(jedis);
            }
        }
        return resuleMap;
    }
}
