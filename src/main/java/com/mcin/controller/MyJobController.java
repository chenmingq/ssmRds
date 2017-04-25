package com.mcin.controller;

import com.alibaba.fastjson.JSON;
import com.mcin.model.MyLoginModel;
import com.mcin.model.ResponseModel;
import com.mcin.model.UserInfoModel;
import com.mcin.service.impl.MyJobService;
import com.mcin.service.impl.RegisterValidateService;
import com.mcin.util.EMUtil;
import com.mcin.util.MD5Uitl;
import com.mcin.util.ToUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mcin on 2017/3/8.
 */
@Controller
@RequestMapping("/home123")
public class MyJobController {

//    @Resource
    private MyJobService myJobService;

    @Resource
    private RegisterValidateService registerService;

    /**
     * 注册信息
     * @param loginModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register123" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String register(MyLoginModel loginModel , HttpServletRequest request){
        Integer result = 0;
        String requestUrl = ToUtil.getHost(request);
        String sessus = "errorAccount";

        if (StringUtils.isNotBlank(loginModel.getEmail()) && StringUtils.isNotBlank(loginModel.getPassWord())){

            // 通过md5 加密后将密码传输到后台
            loginModel.setPassWord(MD5Uitl.MD5LowerCase(loginModel.getPassWord()));
            String ip = ToUtil.getIpAddr(request);
            if (StringUtils.isNotBlank(ip)){
                loginModel.setIp(ip);
            } else{
                loginModel.setIp(EMUtil.DEFAULT_IP.IP_CODE.VALUE);
            }

            result = myJobService.insertLogin(loginModel);
            if (result == EMUtil.REGISTER_STATUS.EXIST_CODE.KEY){
                sessus = "exist";
                return sessus;
            }

            // 如果注册成功 则返回的数据等于 1
            else if (result == EMUtil.REGISTER_STATUS.REGISTER_CODE.KEY){
                //注册
                String email = request.getParameter("email");
//                registerService.processregister(email,requestUrl);//发邮箱激活
                Integer resultEmail = 0;
                if (resultEmail == 1){
                    sessus = "register";
                    return sessus;
                }
            }else {
                sessus = "errorRegister";
                return sessus;
            }
        }
        return sessus;
    }


//    /**
//     * 激活邮箱
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/activeEmail" ,method = { RequestMethod.POST,RequestMethod.GET } )
//    public String activeEmail (HttpServletRequest request, HttpServletResponse response){
//        String action = request.getParameter("action");
//        String email = request.getParameter("email");
//        String activeCode = request.getParameter("activeCode");
//        String sessus = "error";
//        ResponseModel responseModel = new ResponseModel();
//
//
//        if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(action) && StringUtils.isNotBlank(activeCode)){
//            if (action.equals("activate")){
//                Integer emailType = myJobService.selectAccountType(email);
//                Integer result = 0;
//
//                // 对邮件进行激活
//                if (emailType == EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY){
//                    Map<String,Object> map = new HashMap<String ,Object>();
//                    Integer accountType = EMUtil.ACCOUNT_TYPE.IS_ACYIVE_CODE.KEY;
//                    map.put("accountType",accountType);
//                    map.put("email",email);
//                    map.put("activeCode",activeCode);
//
//                    result = myJobService.updateAccountType(map);
//                } else {
//                    responseModel.setResponseCode(EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
//                    responseModel.setResponseMsg(sessus);
//                    return JSON.toJSONString(responseModel);
//                }
//
//                // 对邮件点击激活链接的处理
//                if (result == EMUtil.ACCOUNT_TYPE.IS_ACYIVE_CODE.KEY){
//                    String requestUrl = ToUtil.getHost(request);
//                    PrintWriter out = null;
//                    try {
//                        out = response.getWriter();
//                        out.print("<script  type=\"text/javascript\" charset=\"utf-8\">alert(\"用户您好: <br>邮箱账号"+email+"已激活,请登录吧\");</script>");
//                        response.sendRedirect(requestUrl);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (out != null){
//                            out.flush();
//                            out.close();
//                        }
//                        return null;
//                    }
//                }else {
//                    responseModel.setResponseCode(EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
//                    responseModel.setResponseMsg(sessus);
//                    return JSON.toJSONString(responseModel);
//                }
//            }
//        }
//        responseModel.setResponseCode(EMUtil.RESPONS_STATUS.ERROR_CODE.KEY);
//        responseModel.setResponseMsg(sessus);
//        return JSON.toJSONString(responseModel);
//    }


    /**
     * 登录
     * @param email
     * @param passWord
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = { RequestMethod.POST,RequestMethod.GET })
    public String login (String email, String passWord, Model model,HttpServletResponse response) {
        String sessus = "error";
        Integer result = myJobService.seleceLoginInfo(email,MD5Uitl.MD5LowerCase(passWord));
        if (result == null){
            //账号为空
            return sessus;
        }
        if (result == EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY){
            sessus = "notActive";
        } else if (result == EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY){
            //首次登陆
            sessus = "isFirstLogin";
            Map<String,Object> map = new HashMap<String ,Object>();
            Integer notFirstLogin = EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY;
            map.put("email",email);
            map.put("notFirstLogin",notFirstLogin);
            Integer resultNum = myJobService.updateFirstLogin(map);

            if (resultNum == EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY){
                return sessus;
            } else {
                sessus = "error";
                return sessus;
            }

        } else if (result == EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY){
            // 不是首次登陆
            sessus = "notFirstLogin";
        } else if (result == EMUtil.ERROR_PSD.ERROR_PSD_NUM.KEY) {
            // 密码输入次数 达到5次
            sessus = "errorPsdNum";
        } else if (result == EMUtil.ERROR_PSD.PSD_ERROR_TYPE.KEY){
            // 账号或者密码错误
            sessus = "errorPsd";
        } else if (result == EMUtil.RESPONS_STATUS.ERROR_CODE.KEY){
            // 账号不存在
            sessus = "errorAccount";
        }
        return sessus;
    }


    /**
     * 添加用户基本信息
     * @param userInfoModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertInfo",method = { RequestMethod.POST,RequestMethod.GET })
    public String insertInfo(UserInfoModel userInfoModel){
        if (userInfoModel != null){
            Integer result = myJobService.insertUserInfo(userInfoModel);
            if (result == EMUtil.USER_INFO.INSERT_OK.KEY){

                return "保存成功";
            }else if(result == EMUtil.USER_INFO.INSERT_DEFEATED.KEY){
                return "保存失败";
            } else {
                return "请完整填写数据保存";
            }
        }
        return "";
    }



    /**
     * 查询用户基本信息
     * @param email
     * @return
     */

    @RequestMapping(value = "/userInfo",method = { RequestMethod.POST,RequestMethod.GET })
    public ModelAndView userInfo(String email ,HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        UserInfoModel infoModel = new UserInfoModel();
        String path = request.getContextPath(); //应用名称
        String ip = ToUtil.getIpAddr(request);

        mav.addObject("manage","<li><a href=\"https://www.baidu.com\">管理面板</a></li>");
        mav.addObject("name",email);
        mav.addObject("nickName","HR");
        mav.addObject("ip",ip);
        mav.setViewName("/view/index");
        return  mav;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        request.getSession().invalidate();
        mav.setViewName("redirect:/login");
        return mav;
    }

    /**
     * 登录
     * @param userName
     * @param passWord
     * @param model
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/login",method = { RequestMethod.POST,RequestMethod.GET })
//    public String login (String account, String passWord, Model model) {
//
//        RegisteredModel registeredInfo = new RegisteredModel();
//        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(passWord)) {
//            Integer loginResult = myJobService.login(account,MD5Uitl.MD5LowerCase(passWord));
//            if (loginResult==EMUtil.LOGIN.LOGIN_CODE.KEY){
//
//                // 查询缓存的数据
//                registeredInfo = myJobService.selectCacheUserInfo();
//
//                // 如果为空， 去查询数据库数据
//                if (StringUtils.isBlank(registeredInfo.getAccount())){
//                    registeredInfo = myJobService.selectUserInfo(account);
//                }
//                model.addAttribute("registeredInfo" , registeredInfo);
//            }else if (loginResult==EMUtil.LOGIN.LOGIN_CODE.KEY){
//                model.addAttribute("loginMsg" , "账号或密码错误");
//                return "/login";
//            }else{
//                model.addAttribute("loginMsg" , "登录异常");
//                return "/login";
//            }
//            return JSON.toJSONString(registeredInfo);
//        }else{
//            return "error";
//        }
//    }

}
