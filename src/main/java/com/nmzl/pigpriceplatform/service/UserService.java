package com.nmzl.pigpriceplatform.service;

import com.nmzl.pigpriceplatform.entity.User;

/**
 * @author : zxy
 * @date : 2020/4/3 11:52
 */
public interface UserService {

    /**
     * 注册用户
     * @param user: 用户信息主体
     * @return :
     * @author : zxy
     * @date : 2020/4/6 10:18
     */
    boolean register(User user);

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

}
