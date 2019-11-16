package com.duan.buyer.entity;

import java.util.Date;

public class UserRoleRelation {
    private Long id;

    private String companyCode;

    private String shopOwnerCode;

    private String purchaseCode;

    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getShopOwnerCode() {
        return shopOwnerCode;
    }

    public void setShopOwnerCode(String shopOwnerCode) {
        this.shopOwnerCode = shopOwnerCode == null ? null : shopOwnerCode.trim();
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode == null ? null : purchaseCode.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}