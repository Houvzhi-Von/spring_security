package com.victor.security.service;

import com.victor.security.domain.SysUser;
import com.victor.security.dto.SysUserDto;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:44 2018-12-19
 */
public interface SysUserService {

  SysUserDto getSysUserInfoByUsername(String username);

  Integer registerSysUser(SysUser sysUser);

  String login(String username, String password);

  String refreshToken(String oldToken);

}
