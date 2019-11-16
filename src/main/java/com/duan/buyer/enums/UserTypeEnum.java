package com.duan.buyer.enums;

public enum UserTypeEnum {
    MASTER("1","店主","MASTER"),
    CHEF("2","厨师","CHEF"),
    PURCHASE("3","采购","PURCHASE");

    private String typeIndex;
    private String typeName;
    private String typeCode;

    private UserTypeEnum(String typeIndex,String typeName,String typeCode){
        this.typeCode=typeCode;
        this.typeIndex=typeIndex;
        this.typeName=typeName;
    }

    public String getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(String typeIndex) {
        this.typeIndex = typeIndex;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public static UserTypeEnum findTypeCode(String typeCode){
        if(typeCode==null||typeCode.isEmpty()) return null;
        String upperTypeCode=typeCode.toUpperCase();
        for(UserTypeEnum e:UserTypeEnum.values()){
            if(e.typeCode.equals(upperTypeCode)){
                return e;
            }
        }
        return null;
    }
}
