package com.victor.security.dto;

import java.util.HashMap;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 10:52 2018-12-19
 */
public class ResponseDto extends HashMap<String, Object> {

  private static final long serialVersionUID = -8713837118340960775L;

  /**
   * 成功
   */
  private static final Integer SUCCESS = 200;

  /**
   * 警告
   */
  private static final Integer WARN = 201;

  /**
   * 异常 失败
   */
  private static final Integer FAIL = 500;

  private ResponseDto() {
    put("code", SUCCESS);
    put("msg", "请求成功");
  }

  public static ResponseDto error(Object msg) {
    ResponseDto responseVo = new ResponseDto();
    responseVo.put("code", FAIL);
    responseVo.put("msg", msg);
    return responseVo;
  }

  public static ResponseDto warn(Object msg) {
    ResponseDto responseVo = new ResponseDto();
    responseVo.put("code", WARN);
    responseVo.put("msg", msg);
    return responseVo;
  }

  public static ResponseDto ok(Object msg, Object data) {
    ResponseDto responseVo = new ResponseDto();
    responseVo.put("code", SUCCESS);
    responseVo.put("msg", msg);
    responseVo.put("data", data);
    return responseVo;
  }

  public static ResponseDto ok() {
    return new ResponseDto();
  }

  public static ResponseDto error() {
    return ResponseDto.error("");
  }

  @Override
  public ResponseDto put(String key, Object value) {
    super.put(key, value);
    return this;
  }

}
