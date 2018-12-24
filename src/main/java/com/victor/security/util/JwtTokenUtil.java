package com.victor.security.util;

import com.victor.security.dto.SysUserDto;
import io.jsonwebtoken.*;
import java.io.Serializable;
import java.util.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description: JWT工具类
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 20:05 2018-12-18
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil implements Serializable {

  private String header;
  private String secret;
  private Long expiration;

  /**
   * 从数据声明生成令牌
   */
  private String generateToken(Map<String, Object> claims) {
    Date expirationDate = new Date(System.currentTimeMillis() + expiration);
    return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(
        SignatureAlgorithm.HS512, secret).compact();
  }

  /**
   * 从令牌中获取声明
   */
  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  /**
   * 生成令牌
   */
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>(2);
    claims.put("sub", userDetails.getUsername());
    claims.put("created", new Date());
    return generateToken(claims);
  }

  /**
   * 从令牌获取用户名
   */
  public String getUsernameFromToken(String token) {
    String username;
    try {
      Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  /**
   * 判断令牌是否过期
   */
  public Boolean isTokenExpired(String token) {
    try {
      Claims claims = getClaimsFromToken(token);
      Date expiration = claims.getExpiration();
      return expiration.before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 刷新令牌
   */
  public String refreshToken(String token) {
    String refreshToken;
    try {
      Claims claims = getClaimsFromToken(token);
      claims.put("created", new Date());
      refreshToken = generateToken(claims);
    } catch (Exception e) {
      refreshToken = null;
    }
    return refreshToken;
  }

  /**
   * 验证令牌
   */
  public Boolean validateToken(String token, UserDetails userDetails) {
    SysUserDto sysUserDto = (SysUserDto) userDetails;
    String username = getUsernameFromToken(token);
    return (username.equals(sysUserDto.getUsername()) && !isTokenExpired(token));
  }

}
