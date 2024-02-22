package com.ohgiraffers.mybatisspring.section01.factorybean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 붙였다고 bean이 되는 게 아니라 componentscan 범위 안에 있어야 함
// 처음 만들어진 chap01mybatisspringlecturesourceapplication - componentscan 개념을 가지고 있음 - 자신이 있는 위치
// 자신이 있는 패키지 밑까지를 검색함 - 그 안에 @Configuration이 있어야지 bean으로 등록이 됨
// 다른 데에 쓰면 아무리 @Configuration 붙여도 안 됨 - Chap01이랑 같은 패키지가 아니니까
// 콩이 되는 것 = 의존성 주입이 가능해진다는 것 => 웬만하면 생성자 방식으로 하기
public class MenuService {

    private final SqlSessionTemplate sqlSession;

    // 생성자 방식으로 의존성 주입
    @Autowired
    public MenuService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<MenuDTO> findAllMenuByOrderableStatus(String orderableStatus) {
        return sqlSession.getMapper(MenuMapper.class).selectAllMenuByOrderableStatus(orderableStatus);
        // 하위 구현체
    }

}
