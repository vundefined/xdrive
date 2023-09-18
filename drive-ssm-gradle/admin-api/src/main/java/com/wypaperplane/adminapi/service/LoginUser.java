package com.wypaperplane.adminapi.service;

import com.wypaperplane.syscore.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LoginUser implements UserDetails {

    private SysUser sysUser;

    private Set<String> roleEncodeing;

    private Set<String> menuPermission;

    public LoginUser() {}

    public LoginUser(SysUser sysUser, Set<String> roleEncodeing, Set<String> menuPermission) {
        this.sysUser = sysUser;
        this.roleEncodeing = roleEncodeing;
        this.menuPermission = menuPermission;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> lists = new ArrayList<>();
        Iterator<String> iterator = getMenuPermission().iterator();
        while (iterator.hasNext()) {
            lists.add(new SimpleGrantedAuthority(iterator.next()));
        }
        return lists;
    }

    @Override
    public String getPassword() {
        return getSysUser().getPassword();
    }

    @Override
    public String getUsername() {
        return getSysUser().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getSysUser().getDeleted();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Set<String> getRoleEncodeing() {
        return roleEncodeing;
    }

    public void setRoleEncodeing(Set<String> roleEncodeing) {
        this.roleEncodeing = roleEncodeing;
    }

    public Set<String> getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(Set<String> menuPermission) {
        this.menuPermission = menuPermission;
    }
}
