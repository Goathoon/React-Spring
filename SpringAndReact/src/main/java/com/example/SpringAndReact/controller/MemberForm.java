package com.example.SpringAndReact.controller;

public class MemberForm {
    private String username;
    private String password;
    private Boolean isLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 230206 siwon
    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

}
