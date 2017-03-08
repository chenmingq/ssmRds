package com.mcin.dao;

import com.mcin.model.MyLoginModel;
import com.mcin.model.RegisteredModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Mcin on 2017/3/8.
 */

@Repository
public interface MyJobDao {


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
     * 保存注册的账户密码
     * @param account
     * @param passWord
     * @return
     */
    Integer insertAccount (@Param("account")String account, @Param("passWord") String passWord);

    /**
     * 查询出当前注册用户的主键id
     * @param account
     * @return
     */
    Integer selectLoginID (@Param("account")String account);


    /**
     * 登录验证账号密码
     * @param account
     * @param passWord
     * @return
     */
    MyLoginModel login (@Param("account")String account, @Param("passWord") String passWord);




}
