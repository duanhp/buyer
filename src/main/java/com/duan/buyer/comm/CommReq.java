package com.duan.buyer.comm;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class CommReq {
    private List params;
    private PageSet pageSet;
    private String funcNo;


    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public PageSet getPageSet() {
        return pageSet;
    }

    public void setPageSet(PageSet pageSet) {
        this.pageSet = pageSet;
    }

    public String getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(String funcNo) {
        this.funcNo = funcNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
