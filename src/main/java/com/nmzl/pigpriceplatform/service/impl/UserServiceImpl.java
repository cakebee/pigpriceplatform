package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.exception.RegisterException;
import com.nmzl.pigpriceplatform.pojo.UserRegisterForm;
import com.nmzl.pigpriceplatform.repository.UserRepository;
import com.nmzl.pigpriceplatform.service.UserService;
import com.nmzl.pigpriceplatform.util.Constants;
import com.nmzl.pigpriceplatform.util.JWTUtil;
import com.nmzl.pigpriceplatform.util.JavaMailTool;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author : zxy
 * @date : 2020/4/3 13:51
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(UserRegisterForm form) throws RegisterException
    {
        User user;
        if ((user = userRepository.findByUsername(form.getUsername())) != null
                && !Constants.USER_ROLE_NOT_VERIFIED.equals(user.getRole())) {
            throw new RegisterException(Constants.USER_EXIST);
        }
        user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setRole(Constants.USER_ROLE_NOT_VERIFIED);
        userRepository.save(user);
        String token = JWTUtil.sign(form.getUsername(), form.getPassword());
        JavaMailTool.send(form.getEmail(), token);
        return true;
    }

    @Override
    public boolean verifyEmail(String token) throws RegisterException{
        String userName = JWTUtil.getUsername(token);
        User user;
        if ((user = userRepository.findByUsername(userName)) != null) {
            if (JWTUtil.verify(token, user.getUsername(), user.getPassword())) {
                user.setRole(Constants.USER_ROLE_NORMAL);
                userRepository.save(user);
                return true;
            }
            throw new RegisterException("验证失败，请重试");
        }
        throw new RegisterException("请先注册账号信息");
    }


    @Override
    public boolean del(String userId) {
        return false;
    }

    @Override
    public User getUser(String userName) {
        return userRepository.getOne(userName);
    }

    @Override
    public User getUserInfo(String token) {
        return userRepository.getByToken(token);
    }

}
