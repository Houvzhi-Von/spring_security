package com.victor.security.controller;

import com.victor.security.domain.SysUser;
import com.victor.security.dto.*;
import com.victor.security.service.SysUserService;
import io.swagger.annotations.*;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 认证授权--controller
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 16:44 2018-12-18
 */
@Slf4j
@RestController
@Api(tags = "用户登录认证")
@RequestMapping("/auth")
public class AuthController {

  @Resource
  private SysUserService sysUserService;

  @ApiOperation(value = "用户注册")
  @PostMapping(value = "${jwt.route.register}")
  ResponseDto registerUser(@RequestBody SysUser sysUser) {
    if (sysUser == null) {
      return ResponseDto.warn("注册参数为空");
    } else if (StringUtils.isEmpty(sysUser.getUsername())) {
      return ResponseDto.warn("注册用户账号为空");
    } else if (StringUtils.isEmpty(sysUser.getPassword())) {
      return ResponseDto.warn("注册用户密码为空");
    }

    log.info("registerUser param info:    {}", sysUser);
    SysUserDto sysUserDto = sysUserService.getSysUserInfoByUsername(sysUser.getUsername());
    if (sysUserDto != null) {
      return ResponseDto.warn("用户已存在");
    }
    Integer sysUserId = sysUserService.registerSysUser(sysUser);
    log.info("sysUserId info:   " + sysUserId);
    if (sysUserId > 0) {
      return ResponseDto.ok("success", "用户注册成功，请前往登录!");
    } else {
      return ResponseDto.error("用户注册失败");
    }
  }

  @ApiOperation(value = "用户登录")
  @PostMapping(value = "${jwt.route.login}")
  ResponseDto login(@RequestBody LoginVo loginVo) {
    String username = loginVo.getUsername();
    String password = loginVo.getPassword();
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      return ResponseDto.warn("用户名和密码不能为空");
    }
    try {
      String token = sysUserService.login(username, password);
      return ResponseDto.ok("success", token);
    } catch (UsernameNotFoundException e) {
      log.info("error info   {}", e.getMessage());
      return ResponseDto.error("用户名不正确，请检查");
    } catch (DisabledException e) {
      return ResponseDto.error("该用户已被禁用，请联系管理员");
    }
  }

  @PostMapping(value = "${jwt.route.refresh}")
  @ApiOperation(value = "刷新token令牌")
  ResponseDto refreshToken(@RequestHeader String token) {
    return ResponseDto.ok("success", sysUserService.refreshToken(token));
  }

}
