package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        // Contextfiguration 가져옴

//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println("beanName : " + beanName);
//        }

        // 결과:
        // beanName : org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // beanName : org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // beanName : org.springframework.context.event.internalEventListenerProcessor
        // beanName : org.springframework.context.event.internalEventListenerFactory
        // beanName : contextConfiguration
        // beanName : member


        /* 설명. 1. bean의 id를 이용해서 bean을 가져오는 방법 */
        // 이름으로 콩을 고를 수 있다
//        MemberDTO member = (MemberDTO) context.getBean("member");
        // 결과: member = MemberDTO(sequence=1, id=user01, pwd=pass01, name=홍길동)

        /* 설명. 2. bean의 클래스 메타 정보(bean의 타입)를 전달하여 가져오는 방법 */
        // 클래스의 클래스
        // 타입만 뽑아온 것 - 속성이나 메소드 다 필요없고 타입만 일치하면 꺼내오는 것
//        MemberDTO member = context.getBean(MemberDTO.class);
        // 결과: member = MemberDTO(sequence=1, id=user01, pwd=pass01, name=홍길동)

        /* 설명. 3. bean의 id와 클래스 메타 정보를 전달하여 가져오는 방법 */
        MemberDTO member = context.getBean("member", MemberDTO.class);
        // 제일 완벽하게 꺼내는 방법 - 이름과 타입을 둘 다 넣어주면 헷갈리지 않고 꺼낼 수 있음
        // 결과: member = MemberDTO(sequence=1, id=user01, pwd=pass01, name=홍길동)


        System.out.println("member = " + member);
    }

}
