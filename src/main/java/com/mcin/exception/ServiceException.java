package com.mcin.exception;

/**
 * Created by Mcin on 2017/3/9.
 *
 * 邮件异常信息
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -1708015121235851228L;

    public ServiceException(String message) {
        super(message);
    }


}
