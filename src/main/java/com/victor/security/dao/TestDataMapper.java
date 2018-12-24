package com.victor.security.dao;

import com.victor.security.domain.TestData;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDataMapper {

  int deleteByPrimaryKey(Long id);

  int insert(TestData record);

  int insertSelective(TestData record);

  TestData selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(TestData record);

  int updateByPrimaryKey(TestData record);

  List<TestData> getAllTestData();

}