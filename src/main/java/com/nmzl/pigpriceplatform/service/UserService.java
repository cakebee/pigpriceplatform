package com.nmzl.pigpriceplatform.service;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.pojo.UserRegisterForm;

/**
 * @author : zxy
 * @date : 2020/4/3 11:52
 */
public interface UserService {

    /**
     * 注册用户
     * @param form: 用户注册信息主体
     * @return :
     * @author : zxy
     * @date : 2020/4/6 10:18
     */
    boolean register(UserRegisterForm form);

    /**
     * 使用token验证邮箱
     * @param token: 用于识别用户
     * @author : zxy
     * @date : 2020/5/9 11:37 上午
     */
    boolean verifyEmail(String token);

    /**
     * 根据用户名删除用户
     * @param userName: 用户名
     * @return : true-删除成功；false-删除失败
     * @author : zxy
     * @date : 2020/4/6 10:22
     */
    boolean del(String userName);

    /**
     * 根据获取用户信息
     * @param userName: 用户名
     * @return : 用户主体
     * @author : zxy
     * @date : 2020/4/6 10:23
     */
    User getUser(String userName);


    /**
     * 根据token获取用户信息
     * @param token: 用户登录后后端返回给前端的token
     * @return :
     * @author : zxy
     * @date : 2020/4/26 9:41 下午
     */
    User getUserInfo(String token);
}
