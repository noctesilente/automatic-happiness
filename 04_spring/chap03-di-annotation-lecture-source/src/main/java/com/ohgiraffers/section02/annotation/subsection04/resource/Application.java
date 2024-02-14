package com.ohgiraffers.section02.annotation.subsection04.resource;

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

        PokemonService pokemonService = context.getBean("pokemonServiceResource", PokemonService.class);

        pokemonService.pokemonAttack();
        // 결과:
        // 파이리 불꽃 공격
        // 피카츄 백만볼트
        // 꼬부기 물대포 발사
    }
}
