package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.repository.UserRepository;
import com.nmzl.pigpriceplatform.service.UserService;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author : zxy
 * @date : 2020/4/3 13:51
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        return false;
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
