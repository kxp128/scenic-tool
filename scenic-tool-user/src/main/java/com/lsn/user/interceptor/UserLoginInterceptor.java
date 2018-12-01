
package com.lsn.user.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsn.common.core.LoginAuth;
import com.lsn.common.core.ResultCode;
import com.lsn.common.exception.ExceptionResponse;
import com.lsn.user.model.LoginInfo;
import com.lsn.user.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;


/**
 * 登录拦截
 */
@Component
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @SuppressWarnings("rawtypes")
    @Override
    public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            //拿到方法的注解,如果方法上有该注解那么进行校验
            LoginAuth annotation = hm.getMethodAnnotation(LoginAuth.class);
            if (annotation != null) {
                boolean result = false;
                Integer userId;
                String authorization = request.getHeader(Constants.AUTH);
                //获取参数
                Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                Integer userIdPathVar = (Integer) pathVariables.get(Constants.USER_ID);
                Integer userIdPostVar = Integer.parseInt(request.getParameter(Constants.USER_ID));
                userId = null != userIdPathVar ? userIdPathVar : userIdPostVar;
                //查询redis是否存在并进行校验
                if (!(StringUtils.isEmpty(userId) && StringUtils.isEmpty(authorization))) {
                    result = loginCkeck(authorization, userId);
                }
                //如果校验未通过,返回401状态
                if (!result) {
                    PrintWriter out = response.getWriter();
                    int code = ResultCode.UNAUTHORIZED.code();
                    ExceptionResponse exceptionResponse = new ExceptionResponse(code, "Unauthorized");
                    response.setStatus(code);
                    response.setContentType("application/json;charset=UTF-8");
                    out.print(JSONObject.toJSONString(exceptionResponse));
                }
                return result;
            }
        }
        return super.preHandle(request, response, handler);
    }

    private boolean loginCkeck(final String authorization, final Integer userId) {
        boolean flag = false;
        String key = Constants.USER_TOKEN_PREFIX + authorization + Constants.USER_TOKEN_SUFFIX;
        if (redisTemplate.hasKey(key)) {
            String value = redisTemplate.opsForValue().get(key);
            String jsonStr = (String) JSONObject.parse(value);//将字符串解析成json字符串
            LoginInfo loginInfo = JSONObject.parseObject(jsonStr, LoginInfo.class);//转成实体
            if (loginInfo.getRole().equals("user") && (userId.equals(loginInfo.getUserId()))) {
                flag = true;
            }
        }
        return flag;
    }
}
