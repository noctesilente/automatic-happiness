package com.ohgiraffers.section03.annotationconfig.subsection02.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new GenericXmlApplicationContext("section03/annotationconfig/subsection02/xml/spring-context.xml");

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        // xml 방식은 어노테이션 방식과는 다르게 지정한 것들만 콩으로 만들고 부가적인 콩들이 많이 들어있지 않음
        // 결과:
        // beanName = memberDAO
        // beanName = contextConfiguration
        // beanName = configurationSection03
        // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // beanName = org.springframework.context.event.internalEventListenerProcessor
        // beanName = org.springframework.context.event.internalEventListenerFactory
        // beanName = memberDTO
        // beanName = member
    }
}
