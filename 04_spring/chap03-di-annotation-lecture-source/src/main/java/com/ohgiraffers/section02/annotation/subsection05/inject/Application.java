package com.ohgiraffers.section02.annotation.subsection05.inject;

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

        PokemonService pokemonService = context.getBean("pokemonServiceInject", PokemonService.class);

        pokemonService.pokemonAttack();

        // 설명 1. 결과: 꼬부기 물대포 발사
        // 설명 2. 결과: 파이리 불꽃 공격
        // 설명 3. 결과: 피카츄 백만볼트
    }
}
