package com.mcin.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mcin on 2017/4/16.
 *
 * 获取服务器有关信息
 *
 */
public class RequestUtil {

    public static String getRemoteUser(HttpServletRequest request){
       return request.getRemoteUser();
    }

    public static void main (String[] arg){
        File directory = new File("");//参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);
    }

}
