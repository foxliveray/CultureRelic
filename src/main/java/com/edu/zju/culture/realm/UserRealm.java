package com.edu.zju.culture.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.zju.culture.common.ActiveUser;
import com.edu.zju.culture.common.Constant;
import com.edu.zju.culture.mbg.entity.Permission;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.IPermissionService;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.edu.zju.culture.mbg.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author y4oung
 * @date 2020/3/13 7:10 PM
 * @description
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy   //只有使用的时候才会加载
    private IUserService userService;

    @Autowired
    @Lazy
    private IPermissionService permissionService;

    @Autowired
    @Lazy
    private IRoleService roleService;

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
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();
        User user = activeUser.getUser();
        List<String> permissions = activeUser.getPermissions();
        if(user.getType()==Constant.USER_TYPE_SUPER){
            authorizationInfo.addStringPermission("*:*");
        }else{
            if(null!=permissions&&permissions.size()>0){
                authorizationInfo.addStringPermissions(permissions);
            }
        }
        return authorizationInfo;
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
            //根据用户ID查询percode
            //查询所有权限
            QueryWrapper<Permission> qw=new QueryWrapper<>();
            //设置只能查询权限
            qw.eq("type",Constant.TYPE_PERMISSION);
            qw.eq("available", Constant.AVAILABLE_TRUE);

            //根据用户ID+角色+权限去查询
            Long userId=user.getId();
            //根据用户ID查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(Math.toIntExact(userId));
            //根据角色ID取到权限和菜单ID
            Set<Integer> pids=new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            List<Permission> list=new ArrayList<>();
            //根据角色ID查询权限
            if(pids.size()>0) {
                qw.in("id", pids);
                list=permissionService.list(qw);
            }
            List<String> percodes=new ArrayList<>();
            for (Permission permission : list) {
                percodes.add(permission.getPercode());
            }
            //放到
            activeUser.setPermissions(percodes);
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), credentialsSalt, this.getName());
            return info;
        }
        return null;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return principals.getPrimaryPrincipal() + ":authorization";
    }

    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        return principals.getPrimaryPrincipal() + ":authentication";
    }

    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        return token.getPrincipal() + ":authentication";
    }
}
