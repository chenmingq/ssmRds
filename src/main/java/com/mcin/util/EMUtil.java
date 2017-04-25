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
     * 注册类型
     * @author Mcin
     */
    public enum REGISTER_TYPE {

        REGISTER_PERSONAGE(1,"个人注册");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private REGISTER_TYPE(Integer KEY,String VALUE) {
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

    /**
     * 默认ip
     * @author Mcin
     */
    public enum DEFAULT_IP {
        IP_CODE(1,"127.0.0.1");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private DEFAULT_IP(Integer KEY,String VALUE) {
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
     * 默认错误密码次数
     * @author Mcin
     */
    public enum ERROR_PSD {
        PSD_CODE(0,"默认保存0"),
        PSD_ERROR_CODE(1,"密码错误加一"),
        PSD_ERROR_TYPE(201,"密码输入错误"),
        ERROR_PSD_NUM(5,"密码错误次数不能大于5次");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private ERROR_PSD(Integer KEY,String VALUE) {
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
     * 账号激活状态
     * @author Mcin
     */
    public enum ACCOUNT_TYPE {
        IS_ACYIVE_CODE(1,"激活"),
        NOT_ACYIVE_CODE(2,"未激活");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private ACCOUNT_TYPE(Integer KEY,String VALUE) {
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
     *  是否首次登陆
     * @author Mcin
     */
    public enum FIRST_LOGIN {
        IS_FIRST_LOGIN(1,"首次登陆"),
        NOT_FIRST_LOGIN(3,"不是首次登陆");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private FIRST_LOGIN(Integer KEY,String VALUE) {
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
     * 登录状态
     * @author Mcin
     */
    public enum LOGIN {
        LOGIN_CODE(1,"登录成功"),
        ERROR_CODE(2,"登录失败,账号密码错误");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private LOGIN(Integer KEY,String VALUE) {
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
     *  基本信息添加状态
     * @author Mcin
     */
    public enum USER_INFO {
        INSERT_OK(202,"添加成功"),
        INSERT_DEFEATED(203,"添加失败");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private USER_INFO(Integer KEY,String VALUE) {
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
     * 登录用户类型
     * @author Mcin
     */
    public enum USER_LOGIN_TYPE {
        USER_LOGIN(1,"个人登录"),
        COMPANY_LOGIN(2,"企业登录");

        // 成员变量
        public Integer KEY;
        public String VALUE;
        // 构造方法
        private USER_LOGIN_TYPE(Integer KEY,String VALUE) {
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
