package com.victor.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:44 2018-12-27
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException e) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
    out.flush();
    out.close();
  }

}
