package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pokemonServiceCollection")
public class PokemonService {

    // 포켓몬을 3개 다 받으려면?
    private List<Pokemon> pokemonList;

    @Autowired
    public PokemonService(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public void pokemonAttack() {
        // 모든 element를 꺼내서 그게 하나씩 나오면서 공격할 것
        // forEach 뒤에 오는 건 consumer 타입이 올 수 있음 - 반환형이 없는 메소드가 올 수 있음
        // attack도 반환형이 void인 함수이기 때문에 참조를 해서 쓸 수 있음
        // 참조를 하려면 : 두 개 붙이면 됨
        pokemonList.forEach(Pokemon::attack);
    }
}
