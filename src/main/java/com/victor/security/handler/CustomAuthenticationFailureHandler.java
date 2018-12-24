package com.victor.security.handler;

import com.victor.security.util.CommonUtils;
import com.victor.security.util.Constant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义验证失败handler
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 11:10 2018-12-21
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException e)
      throws IOException, ServletException {
    String msg;
    if (e instanceof UsernameNotFoundException) {
      msg = Constant.USER_IS_NULL;
      CommonUtils.returnResponseJsonBody(response, msg);
    } else if (e instanceof BadCredentialsException) {
      msg = Constant.USER_PASSWORD_ERROR;
      CommonUtils.returnResponseJsonBody(response, msg);
    } else if (e instanceof DisabledException) {
      msg = Constant.USER_NOT_ENABLE;
      CommonUtils.returnResponseJsonBody(response, msg);
    } else {
      msg = Constant.LOGIN_FAIL;
      CommonUtils.returnResponseJsonBody(response, msg);
    }
  }
}
