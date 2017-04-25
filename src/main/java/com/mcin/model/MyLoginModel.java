package com.mcin.model;

/**
 * Created by Mcin on 2017/3/8.
 * 用于登录的model
 */
public class MyLoginModel {

    private Integer loginId;//登录表的id
    private String email; //邮箱 -- 登录账户名
    private String passWord; // 密码
    private Integer passWordCount; //密码输入错误的次数
    private String ip;//注册的ip地址
    private Integer activatedType; // 激活状态
    private Integer firstLogin; //是否首次登陆


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

    public Integer getPassWordCount() {
        return passWordCount;
    }

    public void setPassWordCount(Integer passWordCount) {
        this.passWordCount = passWordCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
}
