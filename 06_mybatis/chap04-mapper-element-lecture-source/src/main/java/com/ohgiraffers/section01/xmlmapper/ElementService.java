package com.ohgiraffers.section01.xmlmapper;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

public class ElementService {
    public void selectResultMapTest() {

        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<MenuDTO> menus = mapper.selectResultMapTest();
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    public void selectResultMapAssociationTest() {
        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        // 단순히 메뉴의 필드만 받는 게 아니고 메뉴와 카테고리를 같이 조회해서 그 둘을 같이 받아줄 수 있는 DTO를 만들기
        List<MenuAndCategoryDTO> menus = mapper.selectResultMapAssociationTest();
        // 메뉴를 기준으로 카테고리와 조인을 하려는 것 - 일부러 순서를 이렇게 쓰심
        menus.forEach(System.out::println);

        // 객체 그래프 탐색 - 객체의 필드가 객체고 또 그 안에 있는 필드를 타고 넘어가고...........
        System.out.println("첫번째 메뉴의 카테고리 명: " + menus.get(0).getCategory().getCategoryName());

        sqlSession.close();
    }

    public void selectResultMapCollectionTest() {
        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<CategoryAndMenuDTO> categories = mapper.selectResultMapCollectionTest();
        categories.forEach(System.out::println);

        sqlSession.close();
    }
}
