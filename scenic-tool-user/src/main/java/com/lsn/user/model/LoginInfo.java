package com.lsn.user.model;

/**
 * 存在于redis中的登录信息
 */
public class LoginInfo {

    private Integer userId;

    private String role;

    public LoginInfo() {
    }

    public LoginInfo(Integer userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



}
