package com.ohgiraffers.section02.annotation.subsection01.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }
        /* 단축키. for each문 자동 생성 = iter 입력 */

        // 세 포켓몬 클래스에 @Component 붙였기 때문에 빈으로 만들어서 관리할 것
        // 어노테이션을 붙였기 때문에 컨테이너 안에 세 개가 들어감

        // 결과:
        // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // beanName = org.springframework.context.event.internalEventListenerProcessor
        // beanName = org.springframework.context.event.internalEventListenerFactory
        // beanName = charmander
        // beanName = pikachu
        // beanName = squirtle
        // beanName = pokemonServicePrimary


        PokemonService pokemonService = context.getBean("pokemonServicePrimary", PokemonService.class);
        pokemonService.pokemonAttack();
        // 결과: 피카츄 백만볼트
        // 피카츄에 @Primary를 달아줬기 때문에 pokemon으로 불렀을 때 피카츄가 나온 것
    }
}
