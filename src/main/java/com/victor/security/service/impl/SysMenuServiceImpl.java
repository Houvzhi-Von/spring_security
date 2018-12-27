package com.victor.security.service.impl;

import com.victor.security.dao.SysRoleMapper;
import com.victor.security.domain.SysRole;
import com.victor.security.service.SysMenuService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:05 2018-12-27
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

  @Resource
  private SysRoleMapper sysRoleMapper;

  @Override
  public List<SysRole> getRolesByMenu(String url) {
    return sysRoleMapper.getRolesByMenu(url);
  }
}
