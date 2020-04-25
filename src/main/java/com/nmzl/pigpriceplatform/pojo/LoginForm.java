package com.nmzl.pigpriceplatform.pojo;

import lombok.Data;


/**
 * @author : zxy
 * @date : 2020/4/4 23:15
 */
@Data
public class LoginForm {

    /** 用户名 */
    private String user;

    /** 密码 */
    private String password;

    /** 记住我 */
    private boolean rememberMe;
}
