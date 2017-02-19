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
}
