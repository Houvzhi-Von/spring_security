package com.victor.security.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victor.security.domain.SysRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Description: security用户实体类
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 17:26 2018-12-18
 */
@Data
@ToString
public class SysUserDto implements UserDetails {

  private Long id;
  private String username;
  @JSONField(serialize = false)
  private String password;
  private String nickName;
  private Integer status;
  private Integer deleteFlag;
  private List<SysRole> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (SysRole role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }
    return authorities;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    if (this.status == 1) {
      return false;
    }
    return true;
  }

}
