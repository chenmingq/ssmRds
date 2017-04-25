package com.mcin.controller.user.info;

import com.mcin.model.user.info.UserInfoModel;
import com.mcin.model.user.info.UserLoginModel;
import com.mcin.service.impl.user.info.UserInfoService;
import com.mcin.service.impl.user.info.UserLoginService;
import com.mcin.util.EMUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mcin on 2017/4/8.
 * 查询用户信息
 */


@Controller
@RequestMapping("/info")
public class UserInfoController {

    @Resource
    private UserLoginService loginService;

    @Resource
    private UserInfoService infoService;


    /**
     * 登录成功后视图返回信息到页面
     * @param request
     * @param loginModel
     * @param response
     * @return
     */
    @RequestMapping(value = "/userInfo" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public ModelAndView userInfo (HttpServletRequest request, UserLoginModel loginModel, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        UserInfoModel infoModel = new UserInfoModel();
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 企业登录
        if (loginModel.getLogin().equals(EMUtil.USER_LOGIN_TYPE.COMPANY_LOGIN.KEY)) {
            // 去查询信息返回

            infoModel = infoService.selectUserInfo(loginModel.getLoginId());


            // 个人登录
        } else if (loginModel.getLogin().equals(EMUtil.USER_LOGIN_TYPE.USER_LOGIN.KEY)) {

            if (loginModel.getFirstLogin().equals(EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY)) {
                Map<String ,Object> map = new HashMap<String ,Object>();
                map.put("loginId",loginModel.getLoginId());
                map.put("firstLogin",EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY);
                loginService.updateFirsLogin(map);

                // 进入控制面板的页面 添加个人信息
                mav.setViewName("/controlPanel/index");
                mav.addObject("email",loginModel.getEmail());
                return mav;
            }

            infoModel = infoService.selectUserInfo(loginModel.getLoginId());
        }

        mav.setViewName("/view/index");
        mav.addObject("infoModel",infoModel);
        return mav;
    }


    /**
     * 跳转到后台管理的页面
     * @param request
     * @param userId
     * @param response
     * @return
     */
    @RequestMapping(value = "/{userId}/toManage" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public ModelAndView toManage (HttpServletRequest request, @PathVariable("userId") Integer userId, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/controlPanel/welcome");
        mav.addObject("userName",request.getSession().getAttribute("userName"));
        return mav;
    }


    /**
     * 对个人信息进行修改保存
     * @param request
     * @param infoModel
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInfo" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String  updateInfo (HttpServletRequest request, UserInfoModel infoModel, HttpServletResponse response) {

        String sessus = "error";

        if (null != infoModel.getUserId() && infoModel.getUserId() > 0) {
            infoService.updateUserInfo(infoModel);
            sessus = "updateOk";
        } else {

        }
        return sessus;
    }


    /**
     * 简历上传和修改
     * @param request
     * @param loginModel
     * @param response
     * @return
     *
     *
     */
    @ResponseBody
    @RequestMapping(value = "/uploadJobFile" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String uploadJobFile (HttpServletRequest request, UserLoginModel loginModel, HttpServletResponse response) {

        return "";
    }

    /**
     * 头像上传和修改
     * @param request
     * @param loginModel
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadHeadImg" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String uploadHeadImg (HttpServletRequest request, UserLoginModel loginModel, HttpServletResponse response) {

        return "";
    }

    /**
     * 修改简历工作信息
     * @param request
     * @param loginModel
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateJobInfo" ,method = { RequestMethod.POST,RequestMethod.GET } )
    public String updateJobInfo (HttpServletRequest request, UserLoginModel loginModel, HttpServletResponse response) {

        return "";
    }




}
