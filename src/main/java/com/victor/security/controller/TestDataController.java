package com.victor.security.controller;

import com.victor.security.dto.ResponseDto;
import com.victor.security.service.TestDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 查询测试数据-controller
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 15:54 2018-12-19
 */
@Slf4j
@RestController
@Api(tags = "数据获取")
@RequestMapping("/data")
public class TestDataController {

  @Resource
  private TestDataService testDataService;

  @GetMapping("")
  @ApiOperation(value = "查询所有数据")
  ResponseDto getAllTestData() {
    return ResponseDto.ok("success", testDataService.getAllTestData());
  }

}
