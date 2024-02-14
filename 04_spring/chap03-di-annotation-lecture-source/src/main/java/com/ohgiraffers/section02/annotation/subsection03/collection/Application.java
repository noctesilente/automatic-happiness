package com.ohgiraffers.section02.annotation.subsection03.collection;

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

        PokemonService pokemonService = context.getBean("pokemonServiceCollection", PokemonService.class);

        pokemonService.pokemonAttack();
        // 설명 1. 결과:
        // 파이리 불꽃 공격
        // 피카츄 백만볼트
        // 꼬부기 물대포 발사
        // 들어가는 순서는 알파벳순!

        // 설명 2. 결과:
        // key: charmander
        // value: com.ohgiraffers.section02.annotation.common.Charmander@72a7c7e0
        // 파이리 불꽃 공격
        // key: pikachu
        // value: com.ohgiraffers.section02.annotation.common.Pikachu@2e4b8173
        // 피카츄 백만볼트
        // key: squirtle
        // value: com.ohgiraffers.section02.annotation.common.Squirtle@70e8f8e
        // 꼬부기 물대포 발사

    }
}
