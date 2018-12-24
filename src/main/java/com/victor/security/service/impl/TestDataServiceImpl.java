package com.victor.security.service.impl;

import com.victor.security.dao.TestDataMapper;
import com.victor.security.domain.TestData;
import com.victor.security.service.TestDataService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 15:56 2018-12-19
 */
@Slf4j
@Service
public class TestDataServiceImpl implements TestDataService {

  @Resource
  private TestDataMapper testDataMapper;

  @Override
  public List<TestData> getAllTestData() {
    return testDataMapper.getAllTestData();
  }
}
