package com.duan.buyer.exception;

public class BaseException extends Exception {
    private String errorCode;
    private String errorMesage;


    public BaseException(String errorCode, String errorMesage){
        this.errorCode=errorCode;
        this.errorMesage=errorMesage;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMesage() {
        return errorMesage;
    }

    public void setErrorMesage(String errorMesage) {
        this.errorMesage = errorMesage;
    }
}
