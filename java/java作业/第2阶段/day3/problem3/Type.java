package com.chenhuiying.hello;

public enum Type {
    CHARGE_TYPE_CHARGED(10131001,"自费"),
    CHARGE_TYPE_FREE(10131002,"免费"),
    CHARGE_TYPE_MEMBER_RIGHTS(10131003,"会员权益");

    Integer number;
    String name;

    private Type(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    static String getNameByValue(Integer number){
        Type[] types = Type.values();
        for(Type type : types){
            if(type.number.equals(number)){
                return type.name;
            }
        }
        return null;
    }
}
