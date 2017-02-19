package com.mcin.dao;

import java.util.List;

/**
 * Created by mcin on 2017/2/4.
 */
public interface TestDao {
    String selectName(String phone);

    /**
     * 登陆session测试
     * @param userName
     * @param password
     */
    void login(String userName, String password);
}
