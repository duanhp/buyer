package com.duan.buyer.enums;

public enum ErrorCodeEnum {

    SUCCESS("0000","成功"),
    FAILURE("9999","失败"),
    PARAMS_ERROR("9998","参数错误"),
    OPT_INVALID("9997","操作无效");


    private String code;
    private String message;

    private ErrorCodeEnum(String code,String message){
        this.code=code;
        this.message=message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
