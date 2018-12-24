package com.victor.security.dao;

import com.victor.security.domain.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleMapper {

  Integer deleteByPrimaryKey(Long id);

  Integer insert(SysUserRole record);

  Integer insertSelective(SysUserRole record);

  SysUserRole selectByPrimaryKey(Long id);

  Integer updateByPrimaryKeySelective(SysUserRole record);

  Integer updateByPrimaryKey(SysUserRole record);

  List<SysUserRole> getSysUserRoleByUserId(@Param("sysUserId") Long sysUserId);

}