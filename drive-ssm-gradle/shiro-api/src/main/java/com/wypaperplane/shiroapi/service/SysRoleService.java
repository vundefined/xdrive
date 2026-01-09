package com.wypaperplane.shiroapi.service;

import com.wypaperplane.shiroapi.entity.SysMenu;
import com.wypaperplane.shiroapi.entity.SysRole;
import com.wypaperplane.shiroapi.mapper.SysMenuMapper;
import com.wypaperplane.shiroapi.mapper.SysRoleMapper;
import com.wypaperplane.shiroapi.vo.SysMenuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysRoleService {

    private final Logger logger = LoggerFactory.getLogger(SysRoleService.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public Set<String> getRoleEncodingSet(List<SysRole> sysRoleList) {
        Set<String> roleEncodingSet = new HashSet<>();
        for (SysRole sysRole: sysRoleList) {
            roleEncodingSet.add(sysRole.getEncoding());
        }
        return roleEncodingSet;
    }

    public Set<String> getMenuPermissionSet(List<SysMenu> sysMenuList) {
        Set<String> menuPermissionSet = new HashSet<>();
        // for (SysMenu sysMenu: sysMenuList) {menuPermissionSet.add(sysMenu.getPermission());}
        return menuPermissionSet;
    }

    public List<SysMenu> getSysMenuList(Set<Integer> menuIdSet) {
        if (ObjectUtils.isEmpty(menuIdSet)) {
            return null;
        }
        Example example = new Example(SysMenu.class);
        example.createCriteria().andIn("id", menuIdSet);
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(example);
        return sysMenuList;
    }

    public List<SysRole> getSysRoleList(Integer roleIds[]) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return null;
        }
        Example example = new Example(SysRole.class);
        example.createCriteria().andIn("id", Arrays.asList(roleIds));
        return sysRoleMapper.selectByExample(example);
    }

    public Set<Integer> getMenuIdSet(List<SysRole> sysRoleList) {
        if (ObjectUtils.isEmpty(sysRoleList)) {
            return null;
        }
        Set<Integer> menuIdSet = new HashSet<>();
        for (SysRole sysRole: sysRoleList) {
            menuIdSet.addAll(Set.of(sysRole.getMenuIds()));
            if (sysRole.getMenuhalfIds().length > 0) {
                menuIdSet.addAll(Set.of(sysRole.getMenuhalfIds()));
            }
        }
        return menuIdSet;
    }

    public List<SysMenuVo> buildTree(List<SysMenu> sysMenuList){
        if (ObjectUtils.isEmpty(sysMenuList)) {
            return new ArrayList<>();
        }
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            sysMenuVoList.add(new SysMenuVo(
                    sysMenu.getId(),
                    sysMenu.getPid(),
                    sysMenu.getSort(),
                    sysMenu.getPath(),
                    sysMenu.getName(),
                    sysMenu.getComponent(),
                    new SysMenuVo.Meta(
                            sysMenu.getTitle(),
                            sysMenu.getIcon(),
                            sysMenu.getUrl(),
                            sysMenu.getType(),
                            sysMenu.getHidden(),
                            sysMenu.getKeepAlive(),
                            sysMenu.getPermission()
                    ),
                    new ArrayList<SysMenuVo>()
            ));
        }
        Map<Integer, List<SysMenuVo>> pidListMap = sysMenuVoList.stream().collect(Collectors.groupingBy(SysMenuVo::getPid));
        sysMenuVoList.stream().forEach(item -> item.setChildren(pidListMap.get(item.getId())));
        return pidListMap.get(0);
    }
}
