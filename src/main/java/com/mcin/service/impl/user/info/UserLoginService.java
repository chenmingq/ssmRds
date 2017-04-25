package com.mcin.service.impl.user.info;

import com.mcin.dao.UserLoginDao;
import com.mcin.model.user.info.UserLoginModel;
import com.mcin.service.user.info.UserLoginInter;
import com.mcin.util.EMUtil;
import com.mcin.util.ToUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Mcin on 2017/4/2.
 *
 * 用户登录和注册的service 实现层
 *
 */

@Service
public class UserLoginService implements UserLoginInter {

    @Resource
    private UserLoginDao userLoginDao;

    /**
     * 用于验证当前注册的邮箱是否注册过
     * @param email
     * @return
     */
    public Integer selectUserRegist(@Param("email") String email) {
        Integer result = 0;
        result = userLoginDao.selectUserRegist(email);
        if (result < 1){
            return  result;
        }
        result = 1;
        return result;
    }

    /**
     * 添加用户信息
     * @param userLogin
     * @return
     */
    public Integer insertUserInfo(UserLoginModel userLogin) {
        Integer result = 0;
        if (userLogin != null){
            userLoginDao.insertUserInfo(userLogin);
            result = 1;
        }
        return result;
    }

    /**
     * 查看邮箱是否激活和返回id
     * @param email
     * @return
     */
    public UserLoginModel selectEmailActive(@Param("email") String email) {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel = userLoginDao.selectEmailActive(email);
        return userLoginModel;
    }

    /**
     * 激活邮箱
     * @param userLoginModel
     * @return
     */
    public Integer emailAction(UserLoginModel userLoginModel) {
        Integer result = 0;
        result = userLoginDao.emailAction(userLoginModel);
        if (result > 0) {
            // 添加用户信息表的登录主键id
            Integer selectUserIdResult = userLoginDao.selectUserId(userLoginModel.getLoginId());
            if (selectUserIdResult < 1){
                userLoginDao.insertUserInfoId(userLoginModel.getLoginId());
            }
            result = 1;
        }
        return result;
    }


    /**
     * 验证登录的账号是否存在
     * @param account
     * @return
     */
    public Integer selectUserInfo(@Param("account") String account) {
        Integer result = 0;

        if (ToUtil.regEmail(account)){
            result = userLoginDao.selectEmailUserInfo(account);
        } else if (ToUtil.regPhone(account)){
            result = userLoginDao.selectPhoneUserInfo(account);
        } else {
            return result;
        }

        if (result != null){
            return result;
        }
        return result;
    }

    /**
     * 保存当前企业
     * @param map
     * @return
     */
    public void insertComPany (Map<String, Object> map) {
        Integer resultExist = userLoginDao.selectComPany(map);
        if (resultExist < 1){
            userLoginDao.insertComPany(map);
        }
    }

    /**
     * 登录信息验证
     * @param loginModel
     * @return
     */
    public UserLoginModel selectUserInfo(UserLoginModel loginModel) {
        UserLoginModel userLogin = new UserLoginModel();
        userLogin = userLoginDao.selectUserInfo(loginModel);
        if (userLogin != null) {
            return  userLogin;
        }
        return null;
    }

    /**
     * 修改账号为非首次登陆
     * @param map
     */
    public void updateFirsLogin(Map<String ,Object> map) {
        userLoginDao.updateFirsLogin(map);
    }


}
