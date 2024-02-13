package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import com.ohgiraffers.common.MemberDAO;
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* 설명. 설정용 클래스도 bean으로 관리되며 이름(bean의 id)을 부여할 수 있다. */
// 이 클래스는 spring container가 참고하는 설정용 클래스라는 걸 나타냄
// Configuartion 클래스도 자동으로 콩으로 관리가 됨 = 이름을 붙일 수도 있음
// 이름을 안 붙이면 클래스 첫번째 글자를 소문자로 해서 빈의 id가 됨
// section01 가서 bean 이름 뽑아보면 거기도 contextConfiguration이 있음! - 자동으로 생성된 것

// ComponentScan = 콩이 어디 있는지 스캔하겠다
// basePackages = "com.ohgiraffers" = 하위 패키지에 있는 모든 클래스에서 어노테이션을 체크해서 콩으로 관리하겠다는 뜻
@Configuration("configurationSection03")

/* 설명. 1. 단순히 범위 지정만으로 @Component 계열의 어노테이션들을 모두 bean으로 관리하는 경우 */
//@ComponentScan(basePackages = "com.ohgiraffers")

/* 설명. 2. 범위 및 필터를 적용해서 bean을 관리하는 경우 */
//@ComponentScan(basePackages = "com.ohgiraffers",
//        excludeFilters = {
//            @ComponentScan.Filter(
//                /* 설명. 2-1. 타입으로 설정 */
////                type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDAO.class})
//
//                /* 설명. 2-2. 어노테이션 종류로 설정 */
////                type = FilterType.ANNOTATION, classes = {org.springframework.stereotype.Repository.class}
//            )
//        })

/* 설명. 3. 범위 및 필터를 적용해서 bean을 관리하는 경우(includeFilters) */
@ComponentScan(basePackages = "com.ohgiraffers", useDefaultFilters = false,
                includeFilters = {
                    @ComponentScan.Filter(
                            type = FilterType.ASSIGNABLE_TYPE,
                            classes = {MemberDTO.class}
                    )
                })

/* 설명.
 *  @ComponentScan은 필터의 경우 개념 정도만 알아도 되지만 기본적으로 @Configuration 클래스가 위치한
 *  패키지와 해당 패키지 하위만 Scan하기 때문에 더 상위 범위나 다른 패키지를 Scan하기 위해 주로 사용된다.
* */

public class ContextConfiguration {

    // 설명. 2-1. 2-2. 쓰고 Application 돌리면 memberDAO 빼고 뜸
    // 결과:
    // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
    // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
    // beanName = org.springframework.context.event.internalEventListenerProcessor
    // beanName = org.springframework.context.event.internalEventListenerFactory
    // beanName = configurationSection03
    // beanName = contextConfiguration
    // beanName = member

    // 설명. 3. DTO만 됨 - configuartionSection03은 패키지가 다르기 때문에 제외되지 않음
    // 결과:
    // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
    // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
    // beanName = org.springframework.context.event.internalEventListenerProcessor
    // beanName = org.springframework.context.event.internalEventListenerFactory
    // beanName = configurationSection03
    // beanName = memberDTO


}
