package com.mcin.util;

/**
 * Created by mcin on 2017/2/5.
 */
public class EMUtil {

    /**
     * 返回码
     * @author Licn
     */
    public enum RESPONS_STATUS {
        SUCCESS_CODE(200,"请求成功"),
        ERROR_CODE(400,"请求失败"),
        TOKEN_CODE(401,"无效的TOKEN");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private RESPONS_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }

    /**
     * 注册状态
     * @author Mcin
     */
    public enum REGISTER_STATUS {
        REGISTER_CODE(1,"注册成功"),
        EXIST_CODE(2,"注册失败,账号已被注册"),
        ERROR_CODE(3,"注册失败");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private REGISTER_STATUS(Integer KEY,String VALUE) {
            this.KEY = KEY;
            this.VALUE = VALUE;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.KEY + "_" + this.VALUE;
        }
    }
}
