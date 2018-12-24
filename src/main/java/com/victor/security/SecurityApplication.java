package com.victor.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 项目启动类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018-12-18
 * @Time: 14:50
 */
@SpringBootApplication
@MapperScan("com.victor.security.dao")
public class SecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class, args);
  }

}

