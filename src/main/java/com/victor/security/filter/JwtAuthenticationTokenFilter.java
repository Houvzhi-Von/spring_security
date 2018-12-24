package com.victor.security.filter;

import com.victor.security.util.CommonUtils;
import com.victor.security.util.Constant;
import com.victor.security.util.JwtTokenUtil;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Description: JWT认证拦截器
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 20:41 2018-12-18
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource
  private UserDetailsService userDetailsService;

  @Resource
  private JwtTokenUtil jwtTokenUtil;

  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  private static final String[] AUTH_WHITE_LIST = {
      "/swagger-ui.html",
      "/webjars/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/v2/api-docs",
      "/auth/**",
      "/static/**"
  };

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String requestUrl = request.getRequestURI();
    boolean flag = false;
    for (String whiteUrl : AUTH_WHITE_LIST) {
      if (antPathMatcher.match(whiteUrl, requestUrl)) {
        flag = true;
      }
    }
    if (flag) {
      chain.doFilter(request, response);
      return;
    }
    if (StringUtils.isEmpty(request.getHeader(jwtTokenUtil.getHeader()))) {
      log.info(Constant.HEAD_IS_NULL);
      return;
    }

    String authHeader = request.getHeader(jwtTokenUtil.getHeader());
    if (StringUtils.isNotEmpty(authHeader)) {
      log.info("authHeader:   {}", authHeader);
      String username = jwtTokenUtil.getUsernameFromToken(authHeader);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        if (!userDetails.isEnabled()) {
          log.info(Constant.USER_NOT_ENABLE);
          CommonUtils.returnResponseJsonBody(response, Constant.USER_NOT_ENABLE);
          return;
        }

        if (jwtTokenUtil.validateToken(authHeader, userDetails)) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } else {
      String tokenIsNullMsg = Constant.TOKEN_IS_NULL;
      log.info(tokenIsNullMsg);
      CommonUtils.returnResponseJsonBody(response, tokenIsNullMsg);
      return;
    }
    chain.doFilter(request, response);
  }

}
