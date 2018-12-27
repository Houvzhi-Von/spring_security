package com.victor.security.dao;

import com.victor.security.domain.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {

  Integer deleteByPrimaryKey(Long id);

  Integer insert(SysRole record);

  Integer insertSelective(SysRole record);

  SysRole selectByPrimaryKey(Long id);

  Integer updateByPrimaryKeySelective(SysRole record);

  Integer updateByPrimaryKey(SysRole record);

  List<SysRole> getRolesByMenu(@Param("url") String url);

}