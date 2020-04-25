package com.nmzl.pigpriceplatform.util;

/**
 * 存放所有常量
 *
 * @author : zxy
 * @date : 2020/4/6 11:52
 */
public class Constants {

    /** user set*/
    public static final String USER_LOGIN_SUCCESS = "登录成功";

    public static final String USER_LOGIN_WRONG_PASSWORD = "密码错误";

    public static final String USER_LOGIN_NO_SUCH_USER = "用户名不存在";

    public static final String USER_LOGIN_FAILED = "登录失败";

    public static final String USER_LOGOUT_SUCCESS = "注销成功";

    /** status code set */
    public static final byte CODE_SUCCESS = 1;

    public static final byte CODE_FAILED = 2;

    /** path set*/
    public static final String PATH_HOME = "";

    /** servers ip set */
    public static final String[] SERVER_IP_LIST = {
        "192.168.232.4",
        "192.168.232.5",
        "192.168.232.6"
    };

    /** msg set */
    public static final String MESSAGE_SUCCESS = "操作成功";

    public static final String MESSAGE_FAILED = "操作失败";
}
