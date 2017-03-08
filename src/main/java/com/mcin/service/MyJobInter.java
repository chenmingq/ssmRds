package com.mcin.service;

import com.mcin.model.MyLoginModel;
import com.mcin.model.RegisteredModel;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Mcin on 2017/3/8.
 */
public interface MyJobInter {
    /**
     * 注册的时候验证账号是否被注册
     * @param account
     * @return
     */
    Integer selectAccountCount(@Param("account") String account);

    /**
     * 注册信息
     * @param registeredModel
     * @return
     */
    Integer insertUserInfo (RegisteredModel registeredModel);

    /**
     * 登录验证账号密码
     * @param userName
     * @param password
     * @return
     */
    MyLoginModel login (@Param("userName")String userName, @Param("password") String password);
}
