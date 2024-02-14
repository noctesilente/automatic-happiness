package com.ohgiraffers.section02.annotation.subsection01.primary;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {

    private Pokemon pokemon;

    /* 설명.
     *  @Autowired는 생랴갛면 매개변수 있는 생성자에 자동으로 작성된다.(자동으로 생성자 주입이 됨)
     *  현재와 같이 Pokemon 타입의 빈이 2개 이상일 경우 @Primary 어노테이션을 통해 하나의 빈만 주입되게 할 수 있다.
    * */
   //@Autowired
    // 컨테이너가 관리하는 빈을 자동 주입 받음
    public PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
        // 하지만 이렇게 하면 3개가 매칭된다고 뜸
        // 원하는 포켓몬 클래스로 가서 @Primary 달기 = 포켓몬을 고른다고 하면 제일 먼저 골라짐!
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
