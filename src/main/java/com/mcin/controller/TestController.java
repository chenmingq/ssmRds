package com.mcin.controller;

import com.mcin.dao.TestDao;
import com.mcin.model.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
     * @return
     */
    @RequestMapping(value="/login"/*,method = { RequestMethod.POST,RequestMethod.GET } */)
    public String login(Model model){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());

//        @RequestParam("json" ) String json, HttpServletResponse resp
//        Map<String,Object> map = FastJsonUtil.jsonToObject(json,Map.class);
       ResponseModel responseModel = new ResponseModel();
       String msg = "<b>"+"TomCat 运行中 " +"<b></br>" + time;
        model.addAttribute("msg",msg);


//       String userName = WebUtil.getParameter(map,"userName");
//       return WebUtil.responseResult(userName, responseModel);
        return "/index";
    }

    @RequestMapping("/names")
    @ResponseBody
    public List<String> name (String phone){

        List<String> list = new ArrayList<String>();

        String education = testDao.selectName(phone);
        if (education!=null){
            list.add(education);
        }
        return  list;
    }

    @RequestMapping( value = "/getRedis"  , method = { RequestMethod.POST,RequestMethod.GET }, produces = "application/json")
    public @ResponseBody List<String> getRedis(){
        Jedis jedis = jedisPool.getResource();
//		jedis.get("*");
		String redisValue = jedis.get("1450291172@qq.comuserName");/*map.keySet();*/
        System.out.println(redisValue+"----------------------");
        List<String> list  = new ArrayList<String>();

        Set<?> setKey = jedis.keys("*");
        Iterator<?> it = setKey.iterator();
        if (it != null) {
            while (it.hasNext()) {
                String string =  (String) it.next();
                list.add(string);
            }
        }
//        if (jedis != null) {
//            jedis.quit();
//        }
        System.out.println("----------------------------------------------- list " + list);
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
