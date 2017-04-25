package com.mcin.service;

import com.mcin.model.MyLoginModel;

import com.mcin.model.UserInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * Created by Mcin on 2017/3/8.
 */
public interface MyJobInter {

    /**
     * 验证注册的邮箱是否被注册
     * @param email
     * @return
     */
    Integer selectEmailExsit(@Param("email") String email);

    /**
     * 保存注册的登录信息
     * @param loginModel
     * @return
     */
    Integer insertLogin (MyLoginModel loginModel);

    /**
     * 查询邮箱激活状态
     * @param email
     * @return
     */
    Integer selectAccountType (@Param("email") String email);


    /**
     * 激活账号
     * @param map
     * @return
     */
    Integer updateAccountType (Map<String ,Object> map);

    /**
     * 根据邮箱查询出登录的信息来验证是否登录成功
     * @param passWord
     * @param email
     * @return
     */
    Integer seleceLoginInfo (@Param("email") String email ,@Param("passWord") String passWord);

    /**
     * 将登录状态改为非首次登陆
     * @param map
     * @return
     */
    Integer updateFirstLogin (Map<String,Object> map);

    /**
     * 查询用户基本信息当前邮箱是否存在
     * @param email
     * @return
     */
    Integer selectUserInfoNum (@Param("email") String email);

    /**
     * 添加用户基本信息
     * @param userInfoModel
     * @return
     */
    Integer insertUserInfo(UserInfoModel userInfoModel);

    /**
     * 查询出用户的基本信息
     * @param email
     * @return
     */
    UserInfoModel selectUserInfo (@Param("email")String email);

    /**
     * 查询出用户的基本信息
     * @param email
     * @return
     */
//    RegisteredModel selectUserInfo (@Param("email")String email);

    /**
     * 用户登录,比较用户名和密码是否正确
     * @param account
     * @param passWord
     * @return
     */
//    Integer login (@Param("account")String account, @Param("passWord") String passWord);

    /**
     * 查询出用户的基本信息
     * @param account
     * @return
     */
//    RegisteredModel selectUserInfo (@Param("account") String account);


    /**
     * 查询出redis的数据
     * @return
     */
//    RegisteredModel selectCacheUserInfo ( );

}
