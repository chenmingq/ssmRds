package com.mcin.model;

/**
 * Created by Mcin on 2017/3/8.
 * 用于登录的model
 */
public class MyLoginModel {

    private String account; //用户名
    private String passWord; // 密码
    private String userName; //姓名
    private String phone; //手机
    private String email; //邮箱
    private String ip; // 注册的ip

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
