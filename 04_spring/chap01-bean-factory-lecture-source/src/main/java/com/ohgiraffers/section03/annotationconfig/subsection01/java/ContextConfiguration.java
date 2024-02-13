package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* 설명. 설정용 클래스도 bean으로 관리되며 이름(bean의 id)을 부여할 수 있다. */
// 이 클래스는 spring container가 참고하는 설정용 클래스라는 걸 나타냄
// Configuartion 클래스도 자동으로 콩으로 관리가 됨 = 이름을 붙일 수도 있음
// 이름을 안 붙이면 클래스 첫번째 글자를 소문자로 해서 빈의 id가 됨
// section01 가서 bean 이름 뽑아보면 거기도 contextConfiguration이 있음! - 자동으로 생성된 것

// ComponentScan = 콩이 어디 있는지 스캔하겠다
// basePackages = "com.ohgiraffers" = 하위 패키지에 있는 모든 클래스에서 어노테이션을 체크해서 콩으로 관리하겠다는 뜻
@Configuration("configurationSection03")
@ComponentScan(basePackages = "com.ohgiraffers")
public class ContextConfiguration {


}
