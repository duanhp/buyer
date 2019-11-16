package com.duan.buyer.comm;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${wx.app.id}")
    private String appId;
    @Value("${wx.app.secret}")
    private String secret;

    @Value("${wx.app.connTimeout:3000}")
    private Integer httpConnectTimeout;



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public Integer getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public void setHttpConnectTimeout(Integer httpConnectTimeout) {
        this.httpConnectTimeout = httpConnectTimeout;
    }
}
