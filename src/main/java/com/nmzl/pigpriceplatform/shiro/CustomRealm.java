package com.nmzl.pigpriceplatform.shiro;

import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.pojo.JWTToken;
import com.nmzl.pigpriceplatform.service.UserService;
import com.nmzl.pigpriceplatform.util.JWTUtil;
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
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    //TODO
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.getPrimaryPrincipal().toString());
        User user = userService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String userName = JWTUtil.getUsername(token);
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        User user = userService.getUser(userName);
        if (user == null) {
            return null;
        }
        //TODO 字符串常量替换
        if (!JWTUtil.verify(token, userName, user.getPassword())) {
            throw new AuthenticationException("认证失败，重新登录");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

}
