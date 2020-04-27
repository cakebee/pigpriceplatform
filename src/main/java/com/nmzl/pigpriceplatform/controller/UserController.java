package com.nmzl.pigpriceplatform.controller;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.pojo.LoginForm;
import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.pojo.MsgFactory;
import com.nmzl.pigpriceplatform.service.UserService;
import com.nmzl.pigpriceplatform.service.impl.UserServiceImpl;
import com.nmzl.pigpriceplatform.util.Constants;
import com.nmzl.pigpriceplatform.util.JWTUtil;
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
@CrossOrigin
@RestController
public class UserController {

    @Resource
    private MsgFactory msgFactory;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(@RequestBody LoginForm loginForm) {
        /*UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(),
                loginForm.getPassword());
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
            System.out.println(ae.getMessage());
            ae.printStackTrace();
            msg.setMessage(Constants.USER_LOGIN_FAILED);
            return msg;
        }

        msg.setCode(Constants.CODE_SUCCESS);
        if (currentUser.isAuthenticated()) {
            msg.setMessage(Constants.USER_LOGIN_SUCCESS);
            HashMap<String, String> map = new HashMap<>(2);
            User user = (User) currentUser.getPrincipal();
            map.put("username", user.getUsername());
            map.put("token", user.getToken());
            msg.setData(map);
        } else {
            token.clear();
            msg.setMessage(Constants.USER_LOGIN_FAILED);
        }
        return msg;*/

        User user = userService.getUser(loginForm.getUsername());
        if (user.getPassword().equals(loginForm.getPassword())) {
            System.out.println(loginForm.getUsername() + loginForm.getPassword());
            return msgFactory.success()
                    .setData(JWTUtil.sign(loginForm.getUsername(),
                            loginForm.getPassword()));
        }
        return msgFactory.fail();
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "/userInfo/{token}", method = RequestMethod.GET)
    public Msg userInfo(@PathVariable String token) {
        User user = userService.getUserInfo(token);
        if (user == null) {
            return msgFactory.fail().setMessage(Constants.USER_LOGIN_NO_SUCH_USER);
        } else {
            return msgFactory.success()
                             .setMessage(Constants.MESSAGE_SUCCESS)
                             .setData(user);
        }
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/success")
    public String safe() {
        return "safe";
    }
}
