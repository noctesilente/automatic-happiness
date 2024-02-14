package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pokemonServiceCollection")
public class PokemonService {
    // 포켓몬을 3개 다 받으려면?

    /* 설명. 같은 타입의 bean이 2개 이상일 경우 List 또는 Map 형태의 컬렉션 형태로 주입 받을 수 있다. */

    /* 필기. 1. 같은 타입의 bean들을 List 형태로 주입할 수 있다. */
//    private List<Pokemon> pokemonList;
//
//    @Autowired
//    public PokemonService(List<Pokemon> pokemonList) {
//        this.pokemonList = pokemonList;
//    }
//
//    public void pokemonAttack() {
//        // 모든 element를 꺼내서 그게 하나씩 나오면서 공격할 것
//        // forEach 뒤에 오는 건 consumer 타입이 올 수 있음 - 반환형이 없는 메소드가 올 수 있음
//        // attack도 반환형이 void인 함수이기 때문에 참조를 해서 쓸 수 있음
//        // 참조를 하려면 : 두 개 붙이면 됨
//        pokemonList.forEach(Pokemon::attack);
//    }


    /* 필기. 2. 같은 타입의 bean들을 map 형태로 주입 받을 수 있다. */
    private Map<String, Pokemon> pokemonMap;

    @Autowired
    public PokemonService(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
    }

    /* 설명. key 값은 bean의 id가 넘어오고, value 값은 bean 객체의 주소값이 넘어온다. */
    public void pokemonAttack() {
        pokemonMap.forEach((k, v) -> {
            System.out.println("key: " + k);
            System.out.println("value: " + v);
            v.attack();
        });
    }
}
