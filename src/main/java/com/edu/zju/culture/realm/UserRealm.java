package com.edu.zju.culture.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.zju.culture.common.ActiveUser;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Wrapper;

/**
 * @author y4oung
 * @date 2020/3/13 7:10 PM
 * @description
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", authenticationToken.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);
        if (null != user) {
            ActiveUser activeUser = new ActiveUser();
            activeUser.setUser(user);
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), credentialsSalt, this.getName());
            return info;
        }
        return null;
    }
}
