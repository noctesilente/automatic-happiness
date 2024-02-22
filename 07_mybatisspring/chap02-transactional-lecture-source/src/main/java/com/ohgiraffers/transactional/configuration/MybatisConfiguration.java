package com.ohgiraffers.transactional.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ohgiraffers.transactional", annotationClass = Mapper.class)
// 매퍼를 다 등록하는 게 아니라 스캔해주는 기능
public class MybatisConfiguration {
}
