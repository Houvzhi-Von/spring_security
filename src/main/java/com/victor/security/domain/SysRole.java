package com.victor.security.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRole {

  private Long id;

  private String roleName;

  private String roleChineseName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName == null ? null : roleName.trim();
  }

  public String getRoleChineseName() {
    return roleChineseName;
  }

  public void setRoleChineseName(String roleChineseName) {
    this.roleChineseName = roleChineseName == null ? null : roleChineseName.trim();
  }
}