package com.mcin.model;

import com.mcin.util.EMUtil;

/**
 * Created by mcin on 2017/2/5.
 */
public class ResponseModel {

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    //默认返回Code码
    public  Integer responseCode = EMUtil.RESPONS_STATUS.SUCCESS_CODE.KEY;

    //默认返回消息
    public  String responseMsg = EMUtil.RESPONS_STATUS.SUCCESS_CODE.VALUE;

    public ResponseModel() {}

    public ResponseModel(Integer responseCode, String responseMsg) {
        super();
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

}
