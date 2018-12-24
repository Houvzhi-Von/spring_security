package com.victor.security.dao;

import com.victor.security.domain.SysRole;

public interface SysRoleMapper {

  Integer deleteByPrimaryKey(Long id);

  Integer insert(SysRole record);

  Integer insertSelective(SysRole record);

  SysRole selectByPrimaryKey(Long id);

  Integer updateByPrimaryKeySelective(SysRole record);

  Integer updateByPrimaryKey(SysRole record);

}