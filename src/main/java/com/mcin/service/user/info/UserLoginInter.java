package com.mcin.service.user.info;

import com.mcin.model.user.info.UserLoginModel;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Mcin on 2017/4/2.
 *
 * 用户登录和注册的接口层
 *
 */


public interface UserLoginInter {

    /**
     * 验证该账户是否注册过
     * @param email
     * @return
     */
    Integer selectUserRegist (@Param("email") String email);

    /**
     * 添加用户的注册信息
     * @param userLogin
     * @return
     */
    Integer insertUserInfo (UserLoginModel userLogin);

    /**
     * 查看邮箱是否激活
     * @param email
     * @return
     */
    UserLoginModel selectEmailActive (@Param("email") String email);

    /**
     * 激活邮箱
     * @param userLoginModel
     * @return
     */
    Integer emailAction (UserLoginModel userLoginModel);


    /**
     * 验证登录的账号是否存在
     * @param account
     * @return
     */
    Integer selectUserInfo (@Param("account") String account);

    /**
     * 查询当前登录的企业和用户账号是否保存过
     * @param map
     * @return
     */
    void insertComPany (Map<String ,Object> map);

    /**
     * 登录信息验证
     * @param loginModel
     * @return
     */
    UserLoginModel selectUserInfo (UserLoginModel loginModel);

    /**
     * 修改账号为非首次登陆
     * @param map
     */
    void updateFirsLogin (Map<String ,Object> map);

}
