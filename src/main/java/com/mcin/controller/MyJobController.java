package com.mcin.controller;

import com.mcin.model.RegisteredModel;
import com.mcin.service.impl.MyJobService;
import com.mcin.util.EMUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Mcin on 2017/3/8.
 */
@Controller
@RequestMapping("/home")
public class MyJobController {

    @Resource
    private MyJobService myJobService;


    /**
     * 注册信息
     * @param registeredModel
     * @param model
     * @param response
     * @return
     */
    @RequestMapping(value = "/register")
    public String register(RegisteredModel registeredModel , Model model , HttpResponse response){
        Integer result = 0;
        if (null != registeredModel){
            result = myJobService.insertUserInfo(registeredModel);
            if (result == EMUtil.REGISTER_STATUS.EXIST_CODE.KEY){
                return "账号已被注册";
            }

            // 如果注册成功 则返回的数据等于 1
            if (result == EMUtil.REGISTER_STATUS.REGISTER_CODE.KEY){
                return "注册成功";
            }
        }
        return "注册失败";
    }

    /**
     * 登录
     * @param userName
     * @param passWord
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(String userName, String passWord, Model model) {

        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(passWord)) {
            myJobService.login(userName,passWord);
        }

        return "";
    }

}
