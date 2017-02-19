package com.mcin.controller;

import com.mcin.dao.TestDao;
import com.mcin.model.ResponseModel;
import com.mcin.util.FastJsonUtil;
import com.mcin.util.WebUtil;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by mcin on 2017/2/4.
 */
@Controller
@RequestMapping("/cmq")
public class TestController {


    @Resource
    private TestDao testDao;
    //单个redis
    @Resource
    private JedisPool jedisPool;


    /**
     * 登陆测试
     * @param json
     * @param resp
     * @return
     */
    @RequestMapping(value="/login"/*,method = { RequestMethod.POST,RequestMethod.GET } */)
    @ResponseBody
    public String login(@RequestParam("json") String json, HttpServletResponse resp){

       Map<String,Object> map = FastJsonUtil.jsonToObject(json,Map.class);
       ResponseModel responseModel = new ResponseModel();
       String userName = WebUtil.getParameter(map,"userName");
       return WebUtil.responseResult(userName, responseModel);
    }

    @RequestMapping("/name")
    @ResponseBody
    public List<String> name (String phone){

        List<String> list = new ArrayList<String>();

        String education = testDao.selectName(phone);
        if (education!=null){
            list.add(education);
        }
        return  list;
    }

    @RequestMapping("/getRedis")
    public @ResponseBody List<String> getRedis(){
        Jedis jedis = jedisPool.getResource();
//		jedis.get("*");
//		String redisValue = jedis.get("*");/*map.keySet();*/

        List<String> list  = new ArrayList<String>();

        Set<?> setKey = jedis.keys("*");
        Iterator<?> it = setKey.iterator();
        if (it != null) {
            while (it.hasNext()) {
                String string =  (String) it.next();
                list.add(string);
            }
        }
        if (jedis != null) {
            jedis.quit();
        }
        return list;
    }

    /**
     * 获取value
     * @return
     */
    @RequestMapping("/getValue")
    @ResponseBody
    public Map<String,String> getValue(){
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = new HashMap<String, String>();
        for (int i = 0 ; i<10 ; i ++){
            String value = jedis.get("m"+i);
            map.put("m"+i,value);
        }

        return map;
    }

    @RequestMapping("/redis")
    public @ResponseBody String redis(){
        Jedis jedis = jedisPool.getResource();
        String sessus = "ok";
        String value = "mc12c";
        String key = "m";

//      boolean flage = false;
//      flage = jedis.exists(key);
//      	if (flage) {
//      		sessus= jedis.set( value ,value);
        for (int i = 0; i < 10; i++) {
//				jedis.lpush("cmq", "cmq"+i);
            jedis.set( key+i ,value+i);
            jedis.set("nihao","21");
        }
//		}
        return sessus;
    }
}