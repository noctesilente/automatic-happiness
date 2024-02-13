package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/* 설명.
 *  section03에서는 ComponentScan과 관련하여 java 클래스와 xml 방식으로 사용하는 방법을 확인할 것이다.
 *  이 중에서도 java 클래스와 @ComponentScan 방식을 자주 사용하고 있다.
* */
public class Application {
    public static void main(String[] args) {
        // container 하나 만듦 - ContextConfiguration(설계도) 기준으로 만듦
        // 그 설계도에 com.ohgiraffer 하위에 있는 걸 전부 스캔해서(ComponentScan 이용)
        // 어노테이션 확인하고 만듦- annotation 달려있는 모든 클래스들이 객체로 만들어짐
        // 각각 하나의 객체로 만들어짐\
        // ComponentScan - 으로 필터 적용도 가능
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // 어떤 콩들 관리하고 있는지 보기
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        // 결과:
        // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // beanName = org.springframework.context.event.internalEventListenerProcessor
        // beanName = org.springframework.context.event.internalEventListenerFactory
        // beanName = configurationSection03
        // beanName = memberDAO
        // beanName = contextConfiguration
        // beanName = member

    }
}
