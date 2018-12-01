package com.lsn.user.service;
import com.lsn.user.model.vo.LoginAuthInfo;
import com.lsn.user.model.Users;
import com.lsn.common.core.Service;


/**
 * Created by kongxiaoping on 2018/11/28 15:07
 * Description:
 */
public interface UsersService extends Service<Users> {

    /**
     *微信小程序登录
     * @param code
     */
    public LoginAuthInfo wxApplogin(String code);

    public void addUser(Users user);

}
