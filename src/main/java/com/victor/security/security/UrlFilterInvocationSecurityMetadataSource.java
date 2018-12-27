package com.victor.security.security;

import com.victor.security.domain.SysRole;
import com.victor.security.service.SysMenuService;
import com.victor.security.util.Constant;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:04 2018-12-27
 */
@Slf4j
@Component
public class UrlFilterInvocationSecurityMetadataSource implements
    FilterInvocationSecurityMetadataSource {

  @Resource
  private SysMenuService sysMenuService;

  @Override
  public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

    //得到请求地址
    String requestUrl = ((FilterInvocation) o).getRequestUrl();
    if (Constant.LOGIN_URL.equals(requestUrl)) {
      return null;
    }

    List<SysRole> roleList = sysMenuService.getRolesByMenu(requestUrl);
    if (roleList != null && roleList.size() > 0) {
      int size = roleList.size();
      String[] roleNames = new String[size];
      for (int i = 0; i < size; i++) {
        roleNames[i] = roleList.get(i).getRoleName();
      }
      return SecurityConfig.createList(roleNames);
    } else {
      log.info("该菜单下无角色");
      return SecurityConfig.createList("ROLE_LOGIN");
    }
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return false;
  }
}
