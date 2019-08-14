package com.example.musjoy.pojo;

public class LoginVo {

    private String token;
    private  String phone;

    public LoginVo(String token, String phone) {
        this.token = token;
        this.phone = phone;
    }

    public LoginVo() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
