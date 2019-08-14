package com.example.musjoy.pojo;

public enum ErrorEnum {

    /**
     * 通用枚举
     */
    ERROR_OF_PARAMETERS("40001", "params is error"),
    LOGIN_OF_FAIL("40002", "login fail"),
    ERROR_OF_USERNOTEXIST("40003", "user in not exit");

    private String code;
    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.msg;
    }
}
