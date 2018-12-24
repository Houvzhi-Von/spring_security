package com.victor.security.dao;

import com.victor.security.domain.SysMenuRole;

public interface SysMenuRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    SysMenuRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
}