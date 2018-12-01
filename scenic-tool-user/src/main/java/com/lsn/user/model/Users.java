package com.lsn.user.model;

import javax.persistence.*;

public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String phone;

    /**
     * 1男 2 女 3未知
     */
    private Byte sex;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 1 启用 2 禁用
     */
    @Column(name = "is_enable")
    private Byte isEnable;

    @Column(name = "phone_location")
    private String phoneLocation;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_date")
    private Integer lastLoginDate;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 小程序openid
     */
    @Column(name = "wxa_open_id")
    private String wxaOpenId;

    /**
     * 微信unionId
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 1 普通用户
     */
    private Byte role;

    public Users() {
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取1男 2 女 3未知
     *
     * @return sex - 1男 2 女 3未知
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置1男 2 女 3未知
     *
     * @param sex 1男 2 女 3未知
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取1 启用 2 禁用
     *
     * @return is_enable - 1 启用 2 禁用
     */
    public Byte getIsEnable() {
        return isEnable;
    }

    /**
     * 设置1 启用 2 禁用
     *
     * @param isEnable 1 启用 2 禁用
     */
    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * @return phone_location
     */
    public String getPhoneLocation() {
        return phoneLocation;
    }

    /**
     * @param phoneLocation
     */
    public void setPhoneLocation(String phoneLocation) {
        this.phoneLocation = phoneLocation;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Byte getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_date - 最后登录时间
     */
    public Integer getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginDate 最后登录时间
     */
    public void setLastLoginDate(Integer lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取小程序openid
     *
     * @return wxa_open_id - 小程序openid
     */
    public String getWxaOpenId() {
        return wxaOpenId;
    }

    /**
     * 设置小程序openid
     *
     * @param wxaOpenId 小程序openid
     */
    public void setWxaOpenId(String wxaOpenId) {
        this.wxaOpenId = wxaOpenId;
    }

    /**
     * 获取微信unionId
     *
     * @return union_id - 微信unionId
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置微信unionId
     *
     * @param unionId 微信unionId
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取1 普通用户
     *
     * @return role - 1 普通用户
     */
    public Byte getRole() {
        return role;
    }

    /**
     * 设置1 普通用户
     *
     * @param role 1 普通用户
     */
    public void setRole(Byte role) {
        this.role = role;
    }
}