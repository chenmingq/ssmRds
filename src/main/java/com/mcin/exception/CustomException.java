package com.mcin.exception;


/**
 *
 * 定义异常状态码
 * @author mcin
 *
 */
public class CustomException extends Exception {
    private String code;

    public CustomException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomException(String code, String msg,Throwable t) {
        super(msg,t);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
