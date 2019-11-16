package com.duan.buyer.service;


import com.duan.buyer.comm.WxSignReq;
import com.duan.buyer.comm.WxSignRes;
import com.duan.buyer.exception.BaseException;
import com.duan.buyer.wxdef.WxLoginReturn;

public interface WxService {

    public WxSignRes sign(WxSignReq req) throws BaseException;


    public String getOpenId(String appid,String secret,String code);


    public WxLoginReturn wxLogin(String jsCode);

}
