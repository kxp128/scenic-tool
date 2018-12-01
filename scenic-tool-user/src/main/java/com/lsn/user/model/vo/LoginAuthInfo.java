package com.lsn.user.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录接口返回对象
 */
@ApiModel
public class LoginAuthInfo {

    @ApiModelProperty("用户ID")
    @JsonProperty("user_id")
    private Integer userId;

    @ApiModelProperty()
    @JsonProperty("auth_token")
    private String authToken;

    public LoginAuthInfo() {
    }

    public LoginAuthInfo(Integer userId, String authToken) {
        this.userId = userId;
        this.authToken = authToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
