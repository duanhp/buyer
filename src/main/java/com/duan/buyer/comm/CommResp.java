package com.duan.buyer.comm;

import com.alibaba.fastjson.JSON;
import com.duan.buyer.enums.ErrorCodeEnum;

import java.util.List;

public class CommResp {

    private boolean success=true;
    private List result;
    private String errorCode=ErrorCodeEnum.SUCCESS.getCode();
    private String errorMessage=ErrorCodeEnum.SUCCESS.getMessage();


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
