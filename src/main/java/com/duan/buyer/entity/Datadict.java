package com.duan.buyer.entity;

public class Datadict {
    private Long id;

    private String dtName;

    private String dtCode;

    private Integer keyNo;

    private String keyName;

    private String keyValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName == null ? null : dtName.trim();
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode == null ? null : dtCode.trim();
    }

    public Integer getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(Integer keyNo) {
        this.keyNo = keyNo;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }
}