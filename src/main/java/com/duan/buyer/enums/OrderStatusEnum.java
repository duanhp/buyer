package com.duan.buyer.enums;

public enum OrderStatusEnum {

    NOT_DEFINATED(0,"待确认"),
    DEFINATED(1,"确认"),
    NOT_PURCHASED(2,"待采购"),
    PURCHASED(3,"采购"),
    CANCELED(4,"取消");

    private int code;
    private String name;

    private OrderStatusEnum(int code,String name){
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
