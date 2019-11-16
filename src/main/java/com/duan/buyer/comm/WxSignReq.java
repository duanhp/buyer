package com.duan.buyer.comm;

import java.util.Map;

public class WxSignReq {

    private String appid;  //公众号ID
    private String code;   //授权code
    private Map<String, Object> params;  //要签名的参数，如：{key1:value1,key2:value2...}


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
