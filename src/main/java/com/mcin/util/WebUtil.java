package com.mcin.util;

import com.alibaba.fastjson.JSON;
import com.mcin.model.ResponseModel;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mcin on 2017/2/5.
 */
public class WebUtil {

    public static final String EMPTY_STRING = "";

    /**
     * 返回数据结果带body
     *
     * @param object
     * @param responseModel
     * @return
     */
    public static String responseResult(Object object,ResponseModel responseModel) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", responseModel.getResponseCode());
        resultMap.put("msg", responseModel.getResponseMsg());
        if(null == object){
            resultMap.put("data", "");
        }else{
            resultMap.put("data", object);
        }
        return JSON.toJSONString(resultMap);
    }

    /**
     * 验证请求json参数有误返回的数据
     * @return
     */
    public static String jsonErrorResult(){
        Map<String,Object> resultMap =new HashMap<String,Object>();
        resultMap.put("code", EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
        resultMap.put("msg", "请求参数有误");
        //resultMap.put("body", "");
        return FastJsonUtil.objectToJson(resultMap);
    }

    /**
     * 验证必传参数null
     * @param json
     * @param strs
     * @return
     */
    public static boolean validateRequest(String json, String... strs) {
        // json为null
        if (null == json || StringUtils.isBlank(json)) {
            return false;
        }
        if (null != strs) {
            Map<String, Object> parameterMap = FastJsonUtil.jsonToObject(json,
                    Map.class);

            for (String str : strs) {
                Object partyIdObj = parameterMap.get(str);
                if (partyIdObj == null) {
                    return false;
                }
            }
        }

        return true;
    }



    /**
     * 获取key参数  默认值
     * @param param_map
     * @param name
     * @param defalutKey
     * @return
     */
    public static final String getParameter(Map<String, Object> param_map,
                                            String name, Object defalutKey) {
        if (null == param_map.get(name) || ("").equals(param_map.get(name))) {
            return defalutKey.toString();
        }
        return param_map.get(name).toString();
    }

    /**
     * 获取key参数
     * @param param_map
     * @param name
     * @return
     */
    public static final String getParameter(Map<String, Object> param_map,
                                            String name) {
        String stringValue = EMPTY_STRING;
        if (null == param_map.get(name) || param_map.get(name).equals("")) {
            return stringValue.toString();
        }
        return param_map.get(name).toString();
    }

    /**
     * 获取url传输的参数
     * @param request
     * @param name
     * @return
     */
    public static final String getParameter(HttpServletRequest request,
                                            String name) {
        String value = request.getParameter(name);
        String stringValue = EMPTY_STRING;
        if (value != null) {
            return value;
        }
        return stringValue;
    }
}
