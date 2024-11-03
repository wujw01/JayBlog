package com.jay.modules.authc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jay.lang.Consts;
import com.jay.modules.authc.entity.MtoUsers;
import com.jay.modules.authc.entity.ShiroRole;
import com.jay.modules.authc.service.MtoUsersService;
import com.jay.modules.authc.service.ShiroUserRoleService;
import com.jay.modules.authc.vo.AccountProfile;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 认证
 * Created by xiang.wei on 2018/10/11
 *
 * @author xiang.wei
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private MtoUsersService mtoUsersService;
    @Autowired
    private ShiroUserRoleService shiroUserRoleService;


    public OAuth2Realm(){
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);

    }

    /**
     * 授权
     * @return
     */
    @Override

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.fromRealm(getName()).iterator().next();
        if (StringUtils.isBlank(username)) {
            return null;
        }
        MtoUsers user = mtoUsersService.selectOne(new EntityWrapper<MtoUsers>().where("username={0}", username));
        if (user == null) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<ShiroRole> roles = shiroUserRoleService.listRoles(user.getId());
//        赋予角色
        for (ShiroRole role : roles) {
            info.addRole(role.getName());
//            赋予权限
            role.getPermissions().forEach(shiroPermission -> info.addStringPermission(shiroPermission.getName()));
        }
        return info;
    }

    /**
     * 认证
     *
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        AccountProfile profile = mtoUsersService.login(upToken.getUsername(), String.valueOf(upToken.getPassword()));
        if (profile.getStatus() == Consts.STATUS_CLOSED) {
            throw new LockedAccountException(profile.getName());
        }
        AccountAuthenticationInfo info = new AccountAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
        info.setProfile(profile);

        return info;
    }


}
