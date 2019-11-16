package com.duan.buyer.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duan.buyer.comm.AppConfig;
import com.duan.buyer.comm.WxAccessTokenRes;
import com.duan.buyer.comm.WxSignReq;
import com.duan.buyer.comm.WxSignRes;
import com.duan.buyer.enums.ErrorCodeEnum;
import com.duan.buyer.exception.BaseException;
import com.duan.buyer.service.WxService;
import com.duan.buyer.utils.SortUtils;
import com.duan.buyer.utils.ToolUtils;
import com.duan.buyer.wxdef.WxLoginReturn;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("wxService")
public class WxServiceImpl implements WxService {

    private static final String appSecret = "1e9acf114d049b88e9a6dc1bfef5246c";
    private static final String appID = "wxb9fb338c479d9143";
    private static Logger log = LogManager.getLogger(WxServiceImpl.class);

    private static Map<String,String> wxSession=new HashMap<>();


    @Autowired
    private AppConfig appConfig;


    private WxAccessTokenRes getAccessToken(String appID, String secret, String code) {

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appID
                + "&secret=" + secret
                + "&code=" + code + "&grant_type=authorization_code";

        String res = null;
        try {
            res = Request.Get(url)
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .execute().returnContent().asString();
            Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        WxAccessTokenRes accessToken = null;
        try {
            accessToken = (WxAccessTokenRes) JSON.parseObject(res, WxAccessTokenRes.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return accessToken;
    }


    private String getTicket(String appID, String secret, String code) {

        String ticket = null;
        //从缓存中获取ticket
        String key = appID + "_" + code;
        ticket = "";// wxTicketRedis.getTicket(key);
        if (ticket != null && !"".equals(ticket)) {
            return ticket;
        }

        //从缓存中获取不到
        WxAccessTokenRes accessToken = getAccessToken(appID, secret, code);

        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken.getAccess_token() + "&type=jsapi";

        String res = "";

        try {
            res = Request.Get(url)
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .execute().returnContent().asString();
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }

        try {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
            Integer errcode = (Integer) map.get("errcode");
            if (errcode != 0) {
                String errmsg = (String) map.get("errmsg");
                log.error("Weixin-getticket: errcode=" + errcode + ",errmsg=" + errmsg);
            } else {
                ticket = (String) map.get("ticket");
                Integer expires = (Integer) map.get("expires_in");
                //保存缓存
                //wxTicketRedis.setTicket(key, ticket, expires);
            }
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }

        return ticket;
    }


    @Override
    public WxSignRes sign(WxSignReq req) throws BaseException {

        WxSignRes res = new WxSignRes();
        res.setNoncestr(ToolUtils.randomString());
        res.setTimestamp(System.currentTimeMillis());
        //..
        String ticket = this.getTicket(req.getAppid(), appSecret, req.getCode());
        if (ticket == null || "".equals(ticket)) {
            throw new BaseException(ErrorCodeEnum.FAILURE.getCode(), "获取微信ticket失败");
        }

        Map<String, Object> map = req.getParams();
        map.put("noncestr", res.getNoncestr());
        map.put("timestamp", res.getTimestamp());
        map.put("jsapi_ticket", ticket);
        Map<String, Object> sortmap = SortUtils.sortMapByKey(map);
        String data = ToolUtils.makeUrlPathParam(sortmap);
        String sha1 = DigestUtils.sha1Hex(data);

        res.setSignature(sha1);
        return res;

    }


    private String httpGet(String url) {
        String res = null;
        try {
            res = Request.Get(url)
                    .connectTimeout(appConfig.getHttpConnectTimeout())
                    .socketTimeout(appConfig.getHttpConnectTimeout())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .execute().returnContent().asString();
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }
        return res;
    }


    @Override
    public String getOpenId(String appid, String secret, String code) {


        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

        String res = "";

        try {
            res = Request.Get(url)
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .execute().returnContent().asString();
            log.info(res);
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }


        return null;
    }


    private String getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appConfig.getAppId()+"&secret="+appConfig.getSecret();
        String res = "";
        JSONObject obj=null;
        try {
            res = httpGet(url);
            obj=JSON.parseObject(res);
            return obj.getString("access_token");
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }
        return null;

        /*
        access_token	string	获取到的凭证
        expires_in	number	凭证有效时间，单位：秒。目前是7200秒之内的值。
        errcode	number	错误码
        errmsg	string	错误信息
        */
    }


    @Override
    public WxLoginReturn wxLogin(String jsCode) {
        String sessionCode=getAccessToken();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appConfig.getAppId() + "&secret=" + appConfig.getSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code&QueryString="+sessionCode;
        String res = "";
        WxLoginReturn wxLoginReturn=null;
        try {
            res = httpGet(url);
            wxLoginReturn=JSON.parseObject(res,WxLoginReturn.class);
            wxSession.put(wxLoginReturn.getOpenid(),sessionCode);
        } catch (Exception e) {
            log.error("Weixin-getticket:" + e.getMessage(), e);
        }


        return wxLoginReturn;
    }


}
