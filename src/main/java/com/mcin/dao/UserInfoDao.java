package com.mcin.dao;

import com.mcin.model.user.info.UserInfoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Mcin on 2017/4/7.
 * 查询用户信息
 */
@Repository
public interface UserInfoDao {


    /**
     * 添加用户基本信息
     * @param infoModel
     * @return
     */
    Integer insertUserInfo (UserInfoModel infoModel);

    /**
     * 修改用户基本信息
     * @param infoModel
     * @return
     */
    void updateUserInfo (UserInfoModel infoModel);

    /**
     * 查询用户的基本信息
     * @param userId
     * @return
     */
    UserInfoModel selectUserInfo (@Param("userId") Integer userId);


}
