package com.victor.security.dao;

import com.victor.security.domain.SysMenu;

public interface SysMenuMapper {

  int deleteByPrimaryKey(Long id);

  int insert(SysMenu record);

  int insertSelective(SysMenu record);

  SysMenu selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(SysMenu record);

  int updateByPrimaryKey(SysMenu record);

}