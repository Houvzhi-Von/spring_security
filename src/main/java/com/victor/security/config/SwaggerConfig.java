package com.victor.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: swagger配置类
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 16:54 2018-12-18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value(value = "${swagger.enable}")
  Boolean swaggerEnabled;

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .enable(swaggerEnabled).select()
        .apis(RequestHandlerSelectors.basePackage("com.victor.security.controller"))
        .paths(PathSelectors.any()).build().pathMapping("");
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Security API")
        .description("小智")
        .contact(new Contact("fhz", "https://loser_xiaozhi.coding.me/blog/", "13718913664@163.com"))
        .version("1.0.0")
        .build();
  }

}
