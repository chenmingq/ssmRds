package com.mcin.controller.user.info;

import com.alibaba.fastjson.JSON;
import com.mcin.model.ResponseModel;
import com.mcin.model.user.info.UserLoginModel;
import com.mcin.service.impl.user.info.UserLoginService;
import com.mcin.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mcin on 2017/4/2.
 * 登录注册相关controller
 */


@Controller
@RequestMapping("/home")
public class LoginController {

    @Resource
    private UserLoginService loginService;

    /**
     * 用户注册
     * @param userLoginModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String register (UserLoginModel userLoginModel, HttpServletRequest request) {
        Integer registerNum = 0;
        String sessus = "error";

        if (StringUtils.isNotBlank(userLoginModel.getEmail()) && StringUtils.isNotBlank(userLoginModel.getPassWord())) {
            registerNum = loginService.selectUserRegist(userLoginModel.getEmail());
            if (registerNum > 0) {
                sessus = "exist";
                return sessus;
            }

            String requestUrl = ToUtil.getHost(request);
            String emailActiveCode = MD5Uitl.MD5LowerCase(userLoginModel.getEmail()); // 邮箱激活码
            String activeCode = emailActiveCode + "_" + new Date().getTime();

            Integer sendResulr = ToUtil.sendEmail(userLoginModel.getEmail(),requestUrl,activeCode);

            if (!sendResulr.equals(1) ) {
                sessus = "errorEmail";
                return sessus;
            }

            if (sendResulr.equals(1)) { //邮件发送成功了， 在邮件发送成功后 在保存数据库是因为 ： 注册成功了，邮件发送失败，用户就激活不了， 也不能进行都对该邮箱二次注册了
                userLoginModel.setPassWord(MD5Uitl.MD5LowerCase(userLoginModel.getPassWord()));
                userLoginModel.setCreateIp(ToUtil.getIpAddr(request));
                userLoginModel.setErrorPsdCount(EMUtil.ERROR_PSD.PSD_CODE.KEY);
                userLoginModel.setActivatedType(EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY);
                userLoginModel.setActiveCode(activeCode);
                userLoginModel.setFirstLogin(EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY);
                userLoginModel.setRegistrationType(EMUtil.REGISTER_TYPE.REGISTER_PERSONAGE.KEY);
                userLoginModel.setCreateTime(new Date());
                Integer result = 0;
                result = loginService.insertUserInfo(userLoginModel);
                if (result == 1) {
                    sessus = "registerOk";
                }
            }
        }
        return  sessus;
    }


    /**
     * 激活邮箱
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activeEmail" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String activeEmail (HttpServletRequest request, HttpServletResponse response){
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String activeCode = request.getParameter("activeCode");
        String sessus = "error";
        ResponseModel responseModel = new ResponseModel();

        if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(action) && StringUtils.isNotBlank(activeCode)) {
            if (action.equals("activate")) {
                UserLoginModel userLoginModel = new UserLoginModel();
                userLoginModel = loginService.selectEmailActive(email);
                if (userLoginModel.getLoginId() != null && userLoginModel.getActivatedType().equals(EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY)){
                    //可以激活
                    userLoginModel.setActivatedType(EMUtil.ACCOUNT_TYPE.IS_ACYIVE_CODE.KEY);
                    userLoginModel.setActiveTime(new Date());
                    Integer result = loginService.emailAction(userLoginModel);

                    responseModel.setResponseCode(EMUtil.RESPONS_STATUS.SUCCESS_CODE.KEY);

                    if (result == 1){
                        String requestUrl = ToUtil.getHost(request);
                        try {
                            response.sendRedirect(requestUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                } else {
                    responseModel.setResponseCode(EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
                }
            } else {
                responseModel.setResponseCode(EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
            }
        }
        responseModel.setResponseMsg(sessus);
        return JSON.toJSONString(responseModel);
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String login (HttpServletRequest request,UserLoginModel loginModel, HttpServletResponse response){
        String sessus = "error" ;
        Map<String ,Object> map = new HashMap<String ,Object>();
        String resultStr = "";
        if (StringUtils.isNotBlank(loginModel.getLoginType()) && StringUtils.isNotBlank(loginModel.getAccount())) {
            String loginType = loginModel.getLoginType();
            String account = loginModel.getAccount();

            if (loginType.equals(Constant.CEO_LOGIN) || loginType.equals(Constant.HR_LOGIN) || loginType.equals( Constant.CPJL_LOGIN)
                    || loginType.equals( Constant.CTO_LOGIN) || loginType.equals( Constant.QITA_LOGIN) ) {

                Integer result = loginService.selectUserInfo(account);
                if (result > 0) {

                    /**
                     * 1：添加企业数据到表
                     * 2：根据账号查询数据
                     */

                    Map<String ,Object> comPanyMap = new HashMap<String,Object>();
                    comPanyMap.put("comPany",loginModel.getComPany());
                    comPanyMap.put("account",loginModel.getAccount());
                    comPanyMap.put("loginType",loginModel.getLoginType());
                    comPanyMap.put("loginIp",ToUtil.getIpAddr(request));
                    loginService.insertComPany(comPanyMap);

                    map = UserUtil.getAccount();
                    map.put("login",EMUtil.USER_LOGIN_TYPE.COMPANY_LOGIN.KEY);
                    resultStr = String.valueOf(result);
                    sessus = "comOk";

                } else {
                    sessus = "comPanyErrorAccount";
                }
            }
         } else if (loginModel.getEmail() != null && loginModel.getPassWord() != null ) {
            // 如果什么都没选中  默认为个人登录
            /**
             * 1：邮箱验证，
             * 2：密码验证
             */
            UserLoginModel userLogin = new UserLoginModel();
            loginModel.setPassWord(MD5Uitl.MD5LowerCase(loginModel.getPassWord()));
            userLogin = loginService.selectUserInfo(loginModel);
            if (userLogin != null) {
                if (userLogin.getErrorPsdCount() < Constant.ERROR_PSD_COUNT) {
                    map = UserUtil.getAccount();
                    map.put("login",EMUtil.USER_LOGIN_TYPE.USER_LOGIN.KEY);
                    map.put("firstLogin",userLogin.getFirstLogin());
                    resultStr = String.valueOf(userLogin.getLoginId());
                    sessus = "meOk";
                } else {
                    sessus = "errorPassWordCount"; // 密码超过五次
                }
            } else {
                sessus = "errorAccount";
            }
        }
        map.put("sessus",sessus);
        map.put("resultStr",resultStr);
        return JSON.toJSONString(map);
    }




}
