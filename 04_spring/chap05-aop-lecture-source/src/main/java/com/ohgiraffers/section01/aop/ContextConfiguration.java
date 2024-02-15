package com.ohgiraffers.section01.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
// -> aspect를 적용할지 안 할지 껐다 켰다 할 수 있음
public class ContextConfiguration {
}
