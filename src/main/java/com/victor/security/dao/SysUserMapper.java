package com.victor.security.dao;

import com.victor.security.domain.SysRole;
import com.victor.security.domain.SysUser;
import com.victor.security.dto.SysUserDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

  Integer deleteByPrimaryKey(Long id);

  Integer insert(SysUser record);

  Integer insertSelective(SysUser record);

  SysUser selectByPrimaryKey(Long id);

  Integer updateByPrimaryKeySelective(SysUser record);

  Integer updateByPrimaryKey(SysUser record);

  SysUserDto getSysUserInfoByUsername(@Param("userName") String userName);

  SysUserDto loadSysUserByUsername(@Param("userName") String userName);

  List<SysRole> getRolesBySysUserId();

}