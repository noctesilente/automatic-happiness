package com.ohgiraffers.section01.xml;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xml.Template.getSqlSession;

public class MenuService {

    /* 1-4 */
    public void findMenuByPrice(int maxPrice) {
        SqlSession sqlSession = getSqlSession();
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menus = mapper.selectMenuByPrice(maxPrice);
        System.out.println("service: ");
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menus = mapper.searchMenu(searchCriteria);
        System.out.println("service: ");
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menus = mapper.searchMenuBySupCategory(searchCriteria);

        if (menus.size() > 0) {
            // menus 안에 담긴 개수가 0보다 많으면
            menus.forEach(System.out::println);
        } else {
            System.out.println("DB와 연동 실패 또는 검색 결과 없음");
        }
        sqlSession.close();
    }

    public void searchMenuByRandomMenuCode(List randomList) {
        SqlSession sqlSession = getSqlSession();
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menus = mapper.searchMenuByRandomMenuCode(randomList);

        if (menus != null && menus.size() > 0) {
            menus.forEach(System.out::println);
        } else {
            System.out.println("DB와 연동 실패 또는 검색 결과 없음");
        }

        sqlSession.close();
    }
}
