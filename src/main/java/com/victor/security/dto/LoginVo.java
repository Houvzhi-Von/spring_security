package com.victor.security.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 11:13 2018-12-20
 */
@Data
public class LoginVo implements Serializable {

  private static final long serialVersionUID = -8713837118340960775L;

  private String username;
  private String password;

}
