package com.lsn.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsn.common.core.AbstractService;
import com.lsn.common.utils.EncodingUtil;
import com.lsn.common.exception.ServiceException;
import com.lsn.user.model.vo.LoginAuthInfo;
import com.lsn.user.model.LoginInfo;
import com.lsn.user.model.Users;
import com.lsn.user.model.WxaLoginResult;
import com.lsn.user.model.WxaResult;
import com.lsn.user.service.UsersService;
import com.lsn.user.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl extends AbstractService<Users> implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

    //小程序appid
    @Value("${wx.app.appid}")
    private String wxaAppid;

    //小程序secret
    @Value("${wx.app.secret}")
    private String wxaSecret;

    //过期时间
    @Value(("${wx.app.tokenExpireTime}"))
    private Integer tokenExpireTime;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginAuthInfo wxApplogin(String code) {
        //向微信登录接口发送请求
        WxaLoginResult result = waxLoginReq(code);
        //检查请求结果
        checkWXResult(result);
        //检查是新老用户
        Users user = checkIsOldUser(result.getOpenid());
        //创建token
        String authToken = getAuthToken(user);
        LoginAuthInfo loginAuthInfo = new LoginAuthInfo(user.getUserId(), authToken);
        return loginAuthInfo;
    }

    @Override
    @Transactional
    public void addUser(Users user) {
        save(user);
    }

    private WxaLoginResult waxLoginReq(String code) {
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", wxaAppid);
        params.put("secret", wxaSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        WxaLoginResult result = restTemplate.getForObject(CODE2SESSION_URL, WxaLoginResult.class, params);
        return result;
    }

    /**
     * 检查是新/老用户,新用户插入用户数据
     * @param openid
     */
    private Users checkIsOldUser(String openid) {
        Users user =  findBy("openid", openid);
        if (user == null) {
            //设置属性
           user = new Users();
           user.setOpenid(openid);
            //创建用户
            ((UserServiceImpl)AopContext.currentProxy()).addUser(user);
        }
        return user;
    }

    /**
     * 检查登录请求返回的状态
     * @param result
     */
    private void checkWXResult(WxaResult result) {
        if (result == null) {
            throw new ServiceException("登录出错");
        }
        Integer errcode = result.getErrcode();
        int successCode = 0;//请求成功
        if (errcode != successCode) {
            log.info("用户登录出错,原因: {}", result.getErrmsg());
            throw new ServiceException("登录出错");
        }
    }

    /**
     * 获取token,没有则创建token,存在redis中
     * 过期时间30天
     * redis中的key格式: token:user:userId+"LoginAuth"(sha256加密):user
     *      token:user: 为前缀,表示为user属于user项目的token
     *      userId+"LoginAuth"(sha256加密)  为token内容
     *      :user  表示该token角色为用户
     * @param user
     * @return
     */
    private String getAuthToken(Users user) {
        Integer userId = user.getUserId();
        String token = EncodingUtil.getSHA256StrJava(userId + Constants.LOGINAUTH);
        String key = Constants.USER_TOKEN_PREFIX + token + Constants.USER_TOKEN_SUFFIX;
        //用户信息作为value
        LoginInfo loginInfo = new LoginInfo(userId, "user");
        String json = JSON.toJSONString(loginInfo);
        redisTemplate.opsForValue().set(key, json, tokenExpireTime, TimeUnit.SECONDS);
        return token;
    }
}
