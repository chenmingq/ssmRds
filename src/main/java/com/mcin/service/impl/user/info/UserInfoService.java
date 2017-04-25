package com.mcin.service.impl.user.info;

import com.mcin.dao.UserInfoDao;
import com.mcin.model.user.info.UserInfoModel;
import com.mcin.service.RedisService;
import com.mcin.service.user.info.UserInfoInter;
import com.mcin.util.Log;
import com.mcin.util.MD5Uitl;
import com.mcin.util.ToUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mcin on 2017/4/8.
 *
 * 用户信息
 *
 */

@Service
public class UserInfoService implements UserInfoInter {


    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisService redisService;


    /**
     * 修改用户基本信息
     * @param infoModel
     * @return
     */
    public void updateUserInfo(UserInfoModel infoModel) {
        userInfoDao.updateUserInfo(infoModel);
        String strId = String.valueOf(infoModel.getUserId());
        try {
            redisService.setCache(infoModel,MD5Uitl.MD5LowerCase(strId)+"info");
        } catch (JedisConnectionException e) {
            Log.info("updateUserInfo**保存数据在redis失败:"+e.getMessage());
        }
    }

    /**
     * 查询用户基本信息
     * @param userId
     * @return
     */
    public UserInfoModel selectUserInfo(@Param("userId") Integer userId) {
        UserInfoModel infoModel = new UserInfoModel();
        String strId = String.valueOf(userId);

        /**
         * 1:保存redis期间 == md5(id)_type
         */

        Map map = new HashMap();
        try {
            map = redisService.getCache(infoModel,MD5Uitl.MD5LowerCase(strId)+"info");
            if (map.size() > 0 ) {
                infoModel = ToUtil.convert(map,UserInfoModel.class);
            } else {
                infoModel = userInfoDao.selectUserInfo(userId);
                // 查询出最新的数据后保存咋redis中
                redisService.setCache(infoModel,MD5Uitl.MD5LowerCase(strId)+"info");
            }
        } catch (JedisConnectionException e) {
            Log.info("redis读取数据失败------>> : 开始执行数据库查询***"+e.getMessage());
            infoModel = userInfoDao.selectUserInfo(userId);
        }
        return infoModel;
    }
}
