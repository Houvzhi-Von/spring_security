package com.victor.security.service.impl;

import com.victor.security.dao.*;
import com.victor.security.dto.SysUserDto;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: security搜索用户实现类
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 19:30 2018-12-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource
  private SysUserMapper sysUserMapper;

  @Resource
  private SysRoleMapper sysRoleMapper;

  @Resource
  private SysUserRoleMapper sysUserRoleMapper;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    SysUserDto sysUserDto = sysUserMapper.loadSysUserByUsername(userName);
    if (sysUserDto == null) {
      throw new UsernameNotFoundException("用户名不正确");
    }
    return sysUserDto;
  }
}
