package com.mcin.service.user.info;

import com.mcin.model.user.info.UserInfoModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mcin on 2017/4/8.
 *
 * 查询用户信息
 *
 */
public interface UserInfoInter {



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
