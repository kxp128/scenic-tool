package com.lsn.user.controller;

import com.lsn.common.core.LoginAuth;
import com.lsn.common.exception.ServiceException;
import com.lsn.user.model.response.SuccessResult;
import com.lsn.user.model.vo.LoginAuthInfo;
import com.lsn.user.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(value = "/users", description = "用户相关", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {


    @Autowired
    private UsersService userService;

    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    @ApiOperation(value = "微信小程序用户登录", notes = "微信小程序用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "用户登录凭证code", required = true, dataType = "String", paramType = "query")//path header
    })
    public SuccessResult<LoginAuthInfo> wxApplogin(@RequestParam(value = "code")String code) {
        LoginAuthInfo info = userService.wxApplogin(code);
        return new SuccessResult<>(info);
    }

    @RequestMapping(value = "/auth.json", method = RequestMethod.GET)
    public void querySlavePaymentMessage() {
        throw new NullPointerException("sdf");
    }

}
