package com.mcin.service.impl;

import com.mcin.dao.MyJobDao;
import com.mcin.model.MyLoginModel;
import com.mcin.model.UserInfoModel;
import com.mcin.service.MyJobInter;
import com.mcin.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * 验证邮箱是否被注册
     * @param email
     * @return
     */
    public Integer selectEmailExsit(@Param("email") String email) {
        Integer emailCount = myJobDao.selectEmailExsit(email);
        return emailCount;
    }

    /**
     * 注册登录的信息
     * @param loginModel
     * @return
     */
    public Integer insertLogin(MyLoginModel loginModel) {
        Integer result = 0;
        if (loginModel != null){
            Integer emailNum = selectEmailExsit(loginModel.getEmail());
            if (emailNum > 0){
                return EMUtil.REGISTER_STATUS.EXIST_CODE.KEY;
            }else {
                loginModel.setPassWordCount(EMUtil.ERROR_PSD.PSD_CODE.KEY);
                loginModel.setActivatedType(EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY);
                loginModel.setFirstLogin(EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY);
                result = myJobDao.insertLogin(loginModel);
                if (result > 0){
                    return EMUtil.REGISTER_STATUS.REGISTER_CODE.KEY;
                }
            }
        }
        return null;
    }

    /**
     * 查询邮箱激活状态
     * @param email
     * @return
     */
    public Integer selectAccountType(@Param("email") String email) {
        Integer result = myJobDao.selectAccountType(email);
        if (result == EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY){
            return EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY;
        } else  {
            return null;
        }
    }

    /**
     * 激活账号
     * @param map
     * @return
     */
    public Integer updateAccountType(Map<String, Object> map) {
        Integer result = myJobDao.updateAccountType(map);
        if (result > 0 ) {
            return EMUtil.ACCOUNT_TYPE.IS_ACYIVE_CODE.KEY;
        }else {
            return null;
        }
    }

    /**
     * 根据邮箱查询出登录的信息来验证是否登录成功
     * @param email
     * @return
     */
    public Integer seleceLoginInfo(@Param("email")String email , @Param("passWord")String passWord) {

        MyLoginModel loginModel = new MyLoginModel();
        if (StringUtils.isNotBlank(email)){
            loginModel = myJobDao.seleceLoginInfo(email);

            //密码输入错误的总次数
            Integer errorPsdNum = EMUtil.ERROR_PSD.ERROR_PSD_NUM.KEY; //5

            if (loginModel.getEmail().equals(email)){

                // 密码不相同
                if ( !loginModel.getPassWord().equals(passWord) && loginModel.getPassWordCount() < errorPsdNum ) {
                    Integer psdErrorNum = EMUtil.ERROR_PSD.PSD_ERROR_CODE.KEY; //1

                    //密码输入的次数累加
                    Integer row = myJobDao.updateErrorPsd(email,psdErrorNum);
                    if (row > 0){
                        //密码输入错误
                        return EMUtil.ERROR_PSD.PSD_ERROR_TYPE.KEY; //201
                    }
                }else if (loginModel.getPassWordCount() >= errorPsdNum){
                    // 密码输入错误的总数
                    return errorPsdNum;
                }

                //账号密码正确
                else if (email.equals(loginModel.getEmail()) && passWord.equals(loginModel.getPassWord())){
                    //账号未激活
                    if (loginModel.getActivatedType().equals(EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY)){
                        return EMUtil.ACCOUNT_TYPE.NOT_ACYIVE_CODE.KEY;//2
                    }
                    // 账号已激活
                    if (loginModel.getFirstLogin() ==  EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY){
                        Integer passWordCount = EMUtil.ERROR_PSD.PSD_CODE.KEY;

                        Map map = new HashMap();
                        map.put("passWordCount",passWordCount);
                        map.put("email",email);

                        myJobDao.updatePasswordCount(map);
                        return EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY; //1

                    }else if (loginModel.getFirstLogin() !=  EMUtil.FIRST_LOGIN.IS_FIRST_LOGIN.KEY){
                        return EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY;
                    }
                }

            }else{
                // 账号不存在
                return EMUtil.RESPONS_STATUS.ERROR_CODE.KEY;
            }
        }
        return null;
    }

    /**
     * 将登录状态改为非首次登陆
     * @param map
     * @return
     */
    public Integer updateFirstLogin(Map<String,Object> map) {
        Integer result = 0;
        if ( null != map ){
            Integer row = myJobDao.updateFirstLogin(map);
            if (row > 0){
                result = EMUtil.FIRST_LOGIN.NOT_FIRST_LOGIN.KEY;
            }
        }
        return result;
    }

    /**
     * 查询用户基本信息当前邮箱是否存在
     * @param email
     * @return
     */
    public Integer selectUserInfoNum(@Param("email") String email) {

        Integer userInfoNum = myJobDao.selectUserInfoNum(email);
        if (userInfoNum > 0){
            return 0;
        }
        return 1;
    }

    /**
     * 添加用户基本信息
     * @param userInfoModel
     * @return
     */
    public Integer insertUserInfo(UserInfoModel userInfoModel) {
        String str = "123";
        if (userInfoModel != null){
            Integer userInfoNum = selectUserInfoNum(userInfoModel.getEmail());
            Jedis jedis = jedisPool.getResource();
            Map map = new HashMap();
            if (userInfoNum <1){
                //添加信息
                Integer row = myJobDao.insertUserInfo(userInfoModel);
                Integer result = 0;
                if (row > 0) {
//                    new ToUtil().setCache(userInfoModel,str);
                    return EMUtil.USER_INFO.INSERT_OK.KEY;
                } else {
                    return EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
                }
            } else {
                // 修改信息
                Integer row = myJobDao.updateUserInfo(userInfoModel);
                if (row > 0){
//                    new ToUtil().setCache(userInfoModel,str);
                    return EMUtil.USER_INFO.INSERT_OK.KEY;
                } else {
                    return EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
                }
            }
        }
        return null;
    }
    /**
     * 查询出用户的基本信息
     * @param email
     * @return
     */
    public UserInfoModel selectUserInfo(@Param("email") String email) {
        UserInfoModel userInfoModel = new UserInfoModel();
        if (StringUtils.isNotBlank(email)){
            Map map = new HashMap();
//            map = new ToUtil().getCache(userInfoModel,email);
            if (map != null){
                try {
                    userInfoModel = ToUtil.mapToBean(map,UserInfoModel.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                userInfoModel = myJobDao.selectUserInfo(email);
            }
        }
        return userInfoModel;
    }


    /**
     * 发送邮件
     * @param email
     * @param host
     */
    public Integer processregister(String email,String host){
//        String activeCode = MD5Uitl.MD5LowerCase(email);
//        char ch = '"';
//        ///邮件的内容
//        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
//        sb.append("<a href=");
//        sb.append(ch);
//        sb.append(host);
//        sb.append("/home/activeEmail.json?action=activate&email=");
//        sb.append(email);
//        sb.append("&activeCode=");
//        sb.append(activeCode);
//        sb.append("\">"+host+"/user/register.json?action=activate&email=");
//        sb.append(email);
//        sb.append("&activeCode=");
//        sb.append(activeCode);
//        sb.append("</a>");
//        //发送邮件
//        send(email, sb.toString());
//        LogInfo.info(" -------  发送邮件 ------------------");
        return 0;
    }

//    /**
//     * 发送邮件
//     * @param toEmail
//     * @param host
//     * @return
//     */
//    public Integer send(String toEmail,String host) {
//        String activeCode = MD5Uitl.MD5LowerCase(toEmail);
//        Integer result = 0;
//        char ch = '"';
//        String content = ToUtil.sendEmailcentot(host,toEmail,activeCode,ch);
//        Session session = SendEmail.getSession();
//        try {
//            LogInfo.info("--开始发生邮件 --"+content);
//
//            // Instantiate a message
//            Message msg = new MimeMessage(session);
//            //Set message attributes
//            msg.setFrom(new InternetAddress(SendEmail.FROM));
//            InternetAddress[] address = {new InternetAddress(toEmail)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject("账号激活邮件");
//            msg.setSentDate(new Date());
//            msg.setContent(content , "text/html;charset=utf-8");
//            //发送信息
//            Transport.send(msg);
//            result = 1;
//        } catch (MessagingException e) {
//            LogInfo.error("邮件发送出现异常 ----------- ：",e.getMessage());
//            e.printStackTrace();
//            return result;
//        }
//        return result;
//    }






    /**
     * 查询出用户的基本信息
     * @param email
     * @return
     */
//    public RegisteredModel selectUserInfo(@Param("email") String email) {
//
//
//
//        return null;
//    }

    /**
     * 注册信息
     * @param registeredModel
     * @return
     */
//    public Integer insertUserInfo(RegisteredModel registeredModel) {
//        Integer register = selectAccountCount(registeredModel.getAccount());
//        if( register > 0) {
//            return EMUtil.REGISTER_STATUS.EXIST_CODE.KEY;
//        } else {
//            //可以注册
//            if (null != registeredModel){
//                // 密码通过md5 加密后保存进数据库
//                String psd = MD5Uitl.MD5LowerCase(registeredModel.getPassWord());
//                registeredModel.setPassWord(psd);
//
//                // 添加注册的账号密码
//                Integer accountResult = myJobDao.insertAccount(registeredModel.getAccount(),registeredModel.getPassWord());
//
////                Integer userId = myJobDao.selectLoginID(registeredModel.getAccount());
////                registeredModel.setUserId(userId);
//
//                // 添加注册的个人信息
//                Integer infoResult = 0;
//                Integer infoAccountNum = myJobDao.selectAccountNum(registeredModel.getAccount());
//                if (infoAccountNum < 1){
//                    infoResult = myJobDao.insertUserInfo(registeredModel);
//                }
//
//                if (infoResult > 0 || accountResult > 0){
//                    return EMUtil.REGISTER_STATUS.REGISTER_CODE.KEY;
//                }else {
//                    //注册失败
//                    return EMUtil.REGISTER_STATUS.ERROR_CODE.KEY;
//                }
//            }else{
//                //注册失败
//                return EMUtil.REGISTER_STATUS.ERROR_CODE.KEY;
//            }
//        }
//    }


    /**
     * 查询用户的基本信息
     * @param account
     * @param passWord
     * @return
     */
//    public Integer login(@Param("account") String account, @Param("passWord") String passWord) {
//        MyLoginModel myLoginModel = new MyLoginModel();
//        myLoginModel =  myJobDao.login(account);
//
//        if (myLoginModel != null ) {
//            if (account.equals (myLoginModel.getAccount()) && MD5Uitl.MD5LowerCase(passWord).equals (myLoginModel.getPassWord())){
//                // 账号密码正确
//                return EMUtil.LOGIN.LOGIN_CODE.KEY;
//            }else{
//                //账号密码错误
//                return EMUtil.LOGIN.ERROR_CODE.KEY;
//            }
//        }
//
//        return null;
//    }

    /**
     * 查询出用户基本信息
     * @param account
     * @return
     */
//    public RegisteredModel selectUserInfo(@Param("account") String account) {
//        RegisteredModel registeredInfo = new RegisteredModel();
//        registeredInfo = myJobDao.selectUserInfo(account);
//        Jedis jedis = jedisPool.getResource();
//
//        if (registeredInfo != null ) {
//            Map map = new HashMap();
//            try {
//                //将信息保存在redis缓存中
//                map = ToUtil.getProperty(registeredInfo);
//                Iterator it = map.entrySet().iterator();
//                String key = "";
//                String value = "";
//
//                while (it.hasNext()){
//                    Map.Entry entry = (Map.Entry)it.next();
//                    if (entry.getValue() !=null){
//                        key = entry.getKey().toString();
//                        value = entry.getValue().toString();
//                        jedis.set(key, value);
////                      jedis.expire(key,10000);
//                    }
//
//                }
//                return registeredInfo;
//
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//                return null;
//            }finally {
//                if (jedis != null) {
//                    jedis.quit();
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 查询出redis的数据
     * @return
     */
//    public RegisteredModel selectCacheUserInfo() {
//        RegisteredModel registeredModel = new RegisteredModel();
//        String value = "";
//        String key = "";
//
//        Jedis redis = jedisPool.getResource();
//        Map map = new HashMap();
//        Map mm = new HashMap();
//        try {
//            map = ToUtil.getProperty(registeredModel);
//            //利用迭代 （Iterator）
//            Set set=map.entrySet();
//            Iterator iterator=set.iterator();
//            while(iterator.hasNext()){
//                Map.Entry<String, Object> enter=(Map.Entry<String, Object>) iterator.next();
//                LogInfo.info(redis.get(enter.getKey()));
//                if (redis.get(enter.getKey()) != null){
//                    mm.put(enter.getKey(),redis.get(enter.getKey()));
//                    LogInfo.info(enter.getKey()+"\t"+enter.getValue());
//                }
//            }
//            registeredModel = ToUtil.convert(mm,RegisteredModel.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            registeredModel = null;
//        }finally{
//            //关闭连接
//            if (redis != null){
//                redis.quit();
//            }
//        }
//
//        return registeredModel;
//    }

}
