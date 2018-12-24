package com.victor.security.util;

import com.alibaba.fastjson.JSONObject;
import com.victor.security.dto.ResponseDto;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

/**
 * @Description: 常用工具类
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 11:13 2018-12-21
 */
@Slf4j
public class CommonUtils {

  /**
   * 异常统一输出体方法
   */
  public static void returnResponseJsonBody(HttpServletResponse response, String msg) {
    try {
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.getWriter().write(JSONObject.toJSONString(ResponseDto.error(msg)));
      response.flushBuffer();
      response.getWriter().close();
    } catch (IOException e) {
      log.info("response body error {}", e.getMessage());
    }
  }

}
