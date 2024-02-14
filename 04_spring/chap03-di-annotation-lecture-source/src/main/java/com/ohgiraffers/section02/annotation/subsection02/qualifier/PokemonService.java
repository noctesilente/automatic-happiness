package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /* 설명.
     *  @Qualifier를 통해 원하는 bean 이름(id)으로 하나의 빈을 주입 받을 수 있다.
     *  (@Primary보다 우선 순위가 높다.)
    * */
    // 필드로 하는 방법 - 필드 주입
//    @Autowired
//    @Qualifier("squirtle")
    // 빈을 주입받는 시점에 primary를 무시하고 내가 원하는 걸 주입받고 싶을 때는 Primary를 이용해서 할 수 있음
    private Pokemon pokemon;

    // 생성자로 하는 방법 - 생성자 주입
    @Autowired
    // @Autowired는 자동으로 달아주는 걸 무시하고 써주는 게 좋음
    public PokemonService(@Qualifier("squirtle") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
