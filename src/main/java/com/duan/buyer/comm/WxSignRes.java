package com.duan.buyer.comm;

public class WxSignRes {

    private String signature; //签名
    private String noncestr;  //随机字符串
    private Long timestamp; //时间戳，单位：毫秒


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
