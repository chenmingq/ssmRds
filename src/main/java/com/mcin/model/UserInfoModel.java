package com.mcin.model;

import java.io.Serializable;

/**
 * Created by Mcin on 2017/3/8.
 * 注册
 */
public class UserInfoModel implements Serializable {

    /**
     * 用户信息表id
     */
    private Integer userInfoId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String passWord;

    /**
     * login表的主键id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户生日
     */
    private String birthday;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 个人网站地址
     */
    private String personalWebsite;

    /**
     * 邮件
     */
    private String email;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String major;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 居住地
     */
    private String apartment;

    /**
     * 籍贯
     */
    private String place;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 注册的ip
     */
    private String ip;

    /**
     * 注册时间
     */
    private String createTime;

    public UserInfoModel() { }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWbsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
