package com.wypaperplane.shiroapi.shiro;

import com.wypaperplane.syscore.entity.SysMenu;
import com.wypaperplane.syscore.entity.SysRole;
import com.wypaperplane.syscore.entity.SysUser;
import com.wypaperplane.syscore.mapper.SysUserMapper;
import com.wypaperplane.shiroapi.service.SysRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;

    public AdminRealm() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(4); // 设置加密次数
        setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /*
     * 权限验证
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) getAvailablePrincipal(principals);
        List<SysRole> sysRoleList = sysRoleService.getSysRoleList(sysUser.getRoleIds());
        Set<Integer> menuIdSet = sysRoleService.getMenuIdSet(sysRoleList);
        List<SysMenu> sysMenuList = sysRoleService.getSysMenuList(menuIdSet);
        Set<String> roles = sysRoleService.getRoleEncodingSet(sysRoleList);
        Set<String> permissions = sysRoleService.getMenuPermissionSet(sysMenuList);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /*
    * 身份验证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username);
        SysUser sysUser = sysUserMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UnknownAccountException("找不到用户 " + username + " 的帐号信息");
        }
        String principal = sysUser.getUsername();
        String credentials = sysUser.getPassword();
        Boolean deleted = sysUser.getDeleted();
        if(Boolean.TRUE.equals(deleted)) {
            throw new LockedAccountException(); // 帐号锁定
        }
        //清除当前主体旧的会话，相当于你在新电脑上登录系统，把你之前在旧电脑上登录的会话挤下去
        // ShiroUtils.deleteCache(principal, true);
        return new SimpleAuthenticationInfo(sysUser, credentials, new AdminByteSource(principal), getName());
    }
}
