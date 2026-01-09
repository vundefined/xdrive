package com.wypaperplane.shiroapi.mapper;

import com.wypaperplane.shiroapi.entity.SysUser;
import com.wypaperplane.shiroapi.vo.SysUserVo;
import com.wypaperplane.syscore.mapper.CustomMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserMapper extends CustomMapper<SysUser> {
  List<SysUserVo> selectPage(@Param("username") String username, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
