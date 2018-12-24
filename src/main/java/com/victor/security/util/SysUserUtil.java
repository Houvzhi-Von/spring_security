package com.victor.security.util;

import com.victor.security.dao.*;
import com.victor.security.dto.SysUserDto;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 17:11 2018-12-19
 */
@Slf4j
@Service
public class SysUserUtil {

  @Resource
  private SysUserMapper sysUserMapper;

  @Resource
  private SysRoleMapper sysRoleMapper;

  @Resource
  private SysUserRoleMapper sysUserRoleMapper;

  public SysUserDto getCurrentUser() {
    SysUserDto sysUserDto = new SysUserDto();
    final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal != null) {
      log.info("principal info:   {}", principal);
      final String[] strings = principal.toString().split("-")[0].split(",");
      final String username = strings[0];
      sysUserDto = sysUserMapper.loadSysUserByUsername(username);
    }
    return sysUserDto;
  }

}
