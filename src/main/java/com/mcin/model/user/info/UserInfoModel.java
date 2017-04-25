package com.mcin.model.user.info;

import java.util.Date;

/**
 * Created by Mcin on 2017/4/5.
 * 用户个人基本信息
 */
public class UserInfoModel {

    /**
     * 信息表id
     */
    private Integer infoId ;

    /**
     * 用户id
     */
    private Integer userId ;

    /**
     * 用户手机号码
     */
    private String  phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 应聘岗位
     */
    private String jobPosition;

    /**
     * 薪资
     */
    private String wages;

    /**
     * 项目地址
     */
    private String openSource;

    /**
     * 项目存放地址 github:git.oschina
     */
    private String openName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     *  生日
     */
    private Date birthday;

    /**
     * 学历
     */
    private String education;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 居住地
     */
    private String apartment;

    /**
     * 籍贯
     */
    private String  place;

    /**
     * 个人网站
     */
    private String  personalWebsite;

    /**
     * 专业
     */
    private String major;

    /**
     * 毕业时间
     */
    private Date graduationTime;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 注册ip
     */
    private String  createIp;

    /**
     * 注册时间
     */
    private Date createTime;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getJobPosition() {
        return jobPosition;
    }

    public void setPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getOpenSource() {
        return openSource;
    }

    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
}
