package com.victor.security.service.impl;

import com.victor.security.dao.SysUserMapper;
import com.victor.security.domain.SysUser;
import com.victor.security.dto.SysUserDto;
import com.victor.security.service.SysUserService;
import com.victor.security.util.Constant;
import com.victor.security.util.JwtTokenUtil;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:44 2018-12-19
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

  @Resource
  private SysUserMapper sysUserMapper;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private JwtTokenUtil jwtTokenUtil;

  @Resource
  private AuthenticationManager authenticationManager;

  @Resource
  private UserDetailsService userDetailsService;

  @Override
  public SysUserDto getSysUserInfoByUsername(String username) {
    SysUserDto sysUserDto = sysUserMapper.getSysUserInfoByUsername(username);
    return sysUserDto;
  }

  @Override
  public Integer registerSysUser(SysUser sysUser) {
    sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
    return sysUserMapper.insertSelective(sysUser);
  }

  @Override
  public String login(String username, String password) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        username, password);
    try {
      String token;
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      token = jwtTokenUtil.generateToken(userDetails);
      return token;
    } catch (UsernameNotFoundException e) {
      throw new UsernameNotFoundException("用户名不正确");
    } catch (DisabledException e) {
      throw new DisabledException("用户被禁用");
    }
  }

  @Override
  public String refreshToken(String oldToken) {
    if (jwtTokenUtil.isTokenExpired(oldToken)) {
      return jwtTokenUtil.refreshToken(oldToken);
    }
    return "error";
  }

}
