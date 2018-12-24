//package com.victor.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.victor.security.dto.LoginVo;
//import com.victor.security.dto.SysUserDto;
//import com.victor.security.util.Constant;
//import com.victor.security.util.JwtTokenUtil;
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * @Description: 登录拦截器
// * @Param:
// * @return:
// * @Author: fhz
// * @Date: 10:18 2018-12-20
// */
//@Slf4j
//@Component
//public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
//
//  private AuthenticationManager authenticationManager;
//
//  @Resource
//  private JwtTokenUtil jwtTokenUtil;
//
//  public JwtLoginFilter(AuthenticationManager authenticationManager) {
//    this.authenticationManager = authenticationManager;
//  }
//
//  /**
//   * 接受解析用户登录凭证
//   */
//  @Override
//  public Authentication attemptAuthentication(HttpServletRequest request,
//      HttpServletResponse response) throws AuthenticationException {
//    try {
//      LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
//      return authenticationManager.authenticate(
//          new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword(),
//              new ArrayList<>()));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  /**
//   * 登录认证成功时执行
//   */
//  @Override
//  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//      FilterChain chain, Authentication authResult) throws IOException, ServletException {
//    SecurityContextHolder.getContext().setAuthentication(authResult);
//    String token = jwtTokenUtil.generateToken((SysUserDto) authResult.getPrincipal());
//    response.addHeader("Authorization", token);
//  }
//
//  /**
//   * 登录认证失败时执行
//   */
//  @Override
//  protected void unsuccessfulAuthentication(HttpServletRequest request,
//      HttpServletResponse response, AuthenticationException failed)
//      throws IOException, ServletException {
//    super.unsuccessfulAuthentication(request, response, failed);
//  }
//
//}
