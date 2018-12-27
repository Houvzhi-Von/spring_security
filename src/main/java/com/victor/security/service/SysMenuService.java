package com.victor.security.service;

import com.victor.security.domain.SysRole;
import java.util.List;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:05 2018-12-27
 */
public interface SysMenuService {

  List<SysRole> getRolesByMenu(String url);

}
