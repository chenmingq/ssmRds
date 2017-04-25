package com.mcin.model.user.info;

import java.util.Date;

/**
 * Created by Mcin on 2017/4/2.
 *
 * 用户注册和登录的实体
 */
public class UserLoginModel {

    /**
     * 登录主键id
     */
    private Integer loginId;

    /**
     * 登录账号  目前默认使用邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String passWord;

    /**
     * 密码错误次数
     */
    private Integer errorPsdCount;

    /**
     * 当前邮箱帐激活状态
     */
    private Integer activatedType;

    /**
     * 是否首次登陆
     */
    private Integer firstLogin;

    /**
     * 邮箱激活码
     */
    private String activeCode;

    /**
     * 注册类型
     */
    private Integer registrationType;

    /**
     * 注册地址的ip
     */
    private String createIp;

    /**
     * 注册的时间
     */
    private Date createTime;

    /**
     * 激活时间
     */
    private Date activeTime;

    /**
     * 登录人员类型
     */
    private String loginType;

    /**
     * 企业名称
     */
    private String comPany;

    /**
     * 企业登陆的写的的邮箱或者手机号码
     */
    private String account;

    /**
     * 登录的类型 （个人还是企业）
     */
    private Integer login;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getErrorPsdCount() {
        return errorPsdCount;
    }

    public void setErrorPsdCount(Integer errorPsdCount) {
        this.errorPsdCount = errorPsdCount;
    }

    public Integer getActivatedType() {
        return activatedType;
    }

    public void setActivatedType(Integer activatedType) {
        this.activatedType = activatedType;
    }

    public Integer getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Integer firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Integer getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getComPany() {
        return comPany;
    }

    public void setComPany(String comPany) {
        this.comPany = comPany;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }
}
