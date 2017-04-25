package com.mcin.handler;

import com.mcin.exception.CustomException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author mcin
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request,
    		HttpServletResponse response, Object handler, Exception e) {
        String code = "9999";
        String msg = e.getMessage();
        if (e instanceof CustomException) {
            code = ((CustomException)e).getCode();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", msg);
        mav.setViewName("error");

        return mav;
    }
}
