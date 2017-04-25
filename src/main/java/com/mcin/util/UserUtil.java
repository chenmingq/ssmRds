package com.mcin.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mcin on 2017/4/7.
 * 用户信息 多次使用
 */
public class UserUtil {

    /**
     * 返回的地址信息
     * @return
     */
    public static Map<String ,Object> getAccount ( ){
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("url","info/userInfo.json");
        return map;
    }

}
