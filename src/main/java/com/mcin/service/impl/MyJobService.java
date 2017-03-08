package com.mcin.service.impl;

import com.mcin.dao.MyJobDao;
import com.mcin.model.MyLoginModel;
import com.mcin.model.RegisteredModel;
import com.mcin.service.MyJobInter;
import com.mcin.util.EMUtil;
import com.mcin.util.MD5Uitl;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by Mcin on 2017/3/8.
 */
@Service
public class MyJobService implements MyJobInter{
    @Resource
    private MyJobDao myJobDao;

    //单个redis
    @Resource
    private JedisPool jedisPool;


    /**
     * 查询该注册的账号的数量
     * @param account
     * @return
     */
    public Integer selectAccountCount(@Param("account") String account) {
       Integer accountCount = myJobDao.selectAccountCount(account);
       return accountCount;
    }

    /**
     * 注册信息
     * @param registeredModel
     * @return
     */
    public Integer insertUserInfo(RegisteredModel registeredModel) {
        Integer register = selectAccountCount(registeredModel.getAccount());
        if( register > 1) {
            return EMUtil.REGISTER_STATUS.EXIST_CODE.KEY;
        } else {
            //可以注册
            if (null != registeredModel){
                // 密码通过md5 加密后保存进数据库
                String psd = MD5Uitl.MD5LowerCase(registeredModel.getPassWord());
                registeredModel.setPassWord(psd);

                // 添加注册的账号密码
                Integer accountResult = myJobDao.insertAccount(registeredModel.getAccount(),registeredModel.getPassWord());

                Integer userId = myJobDao.selectLoginID(registeredModel.getAccount());
                registeredModel.setUserId(userId);

                // 添加注册的个人信息
                Integer infoResult = myJobDao.insertUserInfo(registeredModel);

                if (infoResult > 0 && accountResult > 0){
                    return EMUtil.REGISTER_STATUS.REGISTER_CODE.KEY;
                }else {
                    //注册失败
                    return EMUtil.REGISTER_STATUS.ERROR_CODE.KEY;
                }
            }else{
                //注册失败
                return EMUtil.REGISTER_STATUS.ERROR_CODE.KEY;
            }
        }
    }


    /**
     * 验证登录
     * @param account
     * @param password
     * @return
     */
    public MyLoginModel login(@Param("account") String account, @Param("password") String password) {

        MyLoginModel loginModel = new MyLoginModel();
        loginModel = myJobDao.login(account,password);

        if (account.equalsIgnoreCase(loginModel.getAccount()) && password.equalsIgnoreCase(loginModel.getPassWord())){
            //账号密码一直,登录成功
            return loginModel;
        }
        return null;
    }
}
