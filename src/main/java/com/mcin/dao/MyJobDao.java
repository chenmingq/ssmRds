package com.mcin.dao;

import com.mcin.model.MyLoginModel;

import com.mcin.model.UserInfoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Mcin on 2017/3/8.
 */

@Repository
public interface MyJobDao {


    /**
     * 注册的时候验证邮箱是否被注册
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
     * 添加密码输入错误次数
     * @param email
     * @param psdErrorNum
     * @return
     */
    Integer updateErrorPsd(@Param("email") String email,@Param("psdErrorNum") Integer psdErrorNum );

    /**
     * 登录成功 重置密码错误次数
     * @param map
     * @return
     */
    Integer updatePasswordCount (Map<String ,Object> map);

    /**
     * 根据邮箱查询出登录的信息
     * @param email
     * @return
     */
    MyLoginModel seleceLoginInfo (@Param("email") String email);

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
     * 修改用户信息
     * @param userInfoModel
     * @return
     */
    Integer updateUserInfo (UserInfoModel userInfoModel);

    /**
     * 查询出用户的基本信息
     * @param email
     * @return
     */
    UserInfoModel selectUserInfo (@Param("email")String email);

}
