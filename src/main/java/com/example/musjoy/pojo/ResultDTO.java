package com.example.musjoy.pojo;

import java.io.Serializable;

public class ResultDTO<T> implements Serializable {


    private String msg;

    private String code;

    private T module;

    public ResultDTO() {
        this.code = "200";
        this.msg = "success";
    }

    public ResultDTO(T module) {
        this.code = "200";
        this.msg = "success";
        this.module = module;
    }

    public ResultDTO(Boolean bool) {
        if(bool) {
            this.code = "200";
            this.msg = "success";
            this.module = (T) bool;
        }else {
            this.code = "409";
            this.msg = "操作失败";
            this.module = (T)bool;
        }
    }

    public ResultDTO(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
    }

    public ResultDTO(ErrorEnum errorEnum, T module) {
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
        this.module = module;
    }

    public ResultDTO(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.msg = errorMsg;
    }

    public ResultDTO(String errorCode, String errorMsg, T module) {
        this.code = errorCode;
        this.msg = errorMsg;
        this.module = module;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", module=" + module +
                '}';
    }
}
