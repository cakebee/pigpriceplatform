package com.nmzl.pigpriceplatform.shiro;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @author : zxy
 * @date : 2020/4/3 17:19
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    private UserService userService;

    public CustomRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getUser(userName);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

}
