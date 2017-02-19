package com.mcin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * Created by mcin on 2017/2/5.
 */
public class FastJsonUtil {

    /**
     * json转Object 泛型
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T>  clazz) {
        T t = JSON.parseObject(json,clazz);
        return t;
    }

    /**
     * 对象转JSON字符窜 默认日期格式
     * @param object
     * @return
     */
    public static String objectToJson(Object object){
        //%1$tF %1tT 格式化日期 String.format(formatDate, value);
        String json =FastJsonUtil.toJSONString(object, "%1$tF %1tT",
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullStringAsEmpty);
        return json;
    }

    /**
     * null转""
     * @param object
     * @param features
     * @return
     */
    public static String toJSONString(Object object, final String formatDate,
                                      SerializerFeature...features) {
        SerializeWriter out = new SerializeWriter();
        String s;
        JSONSerializer serializer = new JSONSerializer(out);
        SerializerFeature arr$[] = features;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            SerializerFeature feature = arr$[i$];
            serializer.config(feature, true);
        }

        serializer.getValueFilters().add(new ValueFilter() {
            public Object process(Object obj, String s, Object value) {
                if(null!=value) {
                    //日期统一格式化
                    if(value instanceof java.util.Date) {
                        return String.format(formatDate, value);
                    }
                    return value;
                }else {
                    return null;
                    //return "";
                }
            }
        });
        serializer.write(object);
        s = out.toString();
        out.close();
        return s;
    }

    /**
     * 格式化
     * @param jsonStr
     * @return
     * @author   lizhgb
     * @Date   2015-10-14 下午1:17:35
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     * @author   lizhgb
     * @Date   2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
