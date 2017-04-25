package com.mcin.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆拦截器
 * Created by mcin on 2017/2/4.
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private static final String ADMINSESSION = "adminsession";

        //拦截前处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
        if(sessionObj!=null) {
            return true;
        }
        response.sendRedirect("login.jsp");
//        response.sendRedirect("login.json");
        return false;
    }
    //拦截后处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception { }
    //全部完成后处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception { }
}
