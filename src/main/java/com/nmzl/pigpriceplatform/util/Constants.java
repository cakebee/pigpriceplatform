package com.nmzl.pigpriceplatform.util;

/**
 * 存放所有常量
 *
 * @author : zxy
 * @date : 2020/4/6 11:52
 */
public class Constants {

    /** user set*/
    public static final String USER_ROLE_ADMIN = "admin";

    public static final String USER_ROLE_NORMAL = "user";

    public static final String USER_ROLE_NOT_VERIFIED = "not_verified";

    public static final String USER_LOGIN_SUCCESS = "登录成功";

    public static final String USER_LOGIN_WRONG_PASSWORD = "密码错误";

    public static final String USER_LOGIN_NO_SUCH_USER = "用户名不存在";

    public static final String USER_LOGIN_FAILED = "登录失败";

    public static final String USER_LOGOUT_SUCCESS = "注销成功";

    public static final String USER_EXIST = "用户名已存在";

    public static final String USER_EMAIL_NOT_VERIFIED = "用户邮箱未验证";

    /** status code set */
    public static final int CODE_SUCCESS = 20000;

    public static final int CODE_FAILED = 2;

    /** path set*/
    public static final String PATH_HOME = "";

    public static final String PATH_SHELL_TEST = "/Users/zxy/Temp/test.sh";

    public static final String PATH_SHELL_COLLECT = "/Users/zxy/Temp/collect.sh";

    public static final String PATH_SHELL_CALCULATE_AVG = "/Users/zxy/Temp/calculate/avg.sh";

    /** servers ip set */
    public static final String[] SERVER_IP_LIST = {
        "192.168.232.4",
        "192.168.232.5",
        "192.168.232.6"
    };

    /** msg set */
    public static final String MESSAGE_SUCCESS = "操作成功";

    public static final String MESSAGE_FAILED = "操作失败";

    /** time set */
    public static final long TIME_TOKEN_EXPIRE = 24 * 60 * 60 * 1000;

    /** dataBase table name */
    public static final String TABLE_NAME_AVG_PRICE = "avg_price";

    /** url set */
    public static final String URL_WEB_ROOT = "localhost";

    public static final String URL_WEB_VERIFY_EMAIL = URL_WEB_ROOT + "/user/verify/";

    public static final String URL_HDFS = "hdfs://localhost:9000/input/price.csv";
}
