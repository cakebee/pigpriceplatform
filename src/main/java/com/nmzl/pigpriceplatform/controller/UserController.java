package com.nmzl.pigpriceplatform.controller;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.pojo.LoginForm;
import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.pojo.MsgFactory;
import com.nmzl.pigpriceplatform.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;


/**TODO
 * 用户相关操作
 * @author : zxy
 * @date : 2020/4/2 09:39
 */
@RestController
@CrossOrigin
public class UserController {

    @Resource
    private MsgFactory msgFactory;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg hello(@RequestBody LoginForm loginForm) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUser(),
                loginForm.getPassword(),
                loginForm.isRememberMe());
        Subject currentUser = SecurityUtils.getSubject();
        Msg msg = msgFactory.fail();
        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            msg.setMessage(Constants.USER_LOGIN_WRONG_PASSWORD);
            return msg;
        } catch (UnknownAccountException uae) {
            msg.setMessage(Constants.USER_LOGIN_NO_SUCH_USER);
            return msg;
        } catch (AuthenticationException ae) {
            msg.setMessage(Constants.USER_LOGIN_FAILED);
            return msg;
        }

        msg.setCode(Constants.CODE_SUCCESS);
        if (currentUser.isAuthenticated()) {
            msg.setMessage(Constants.USER_LOGIN_SUCCESS);
            HashMap<String, String> map = new HashMap<>(2);
            User user = (User) currentUser.getPrincipal();
            map.put("username", user.getUsername());
            msg.setData(map);
        } else {
            token.clear();
            msg.setMessage(Constants.USER_LOGIN_FAILED);
        }
        return msg;
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/success")
    public String safe() {
        return "safe";
    }
}
