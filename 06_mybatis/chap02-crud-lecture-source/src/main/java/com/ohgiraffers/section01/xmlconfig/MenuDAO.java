package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/* 2-4 */
public class MenuDAO {

    /* 4-6 */
    public List<MenuDTO> selectAllMenus(SqlSession sqlSession) {

        /* 6-1 */
        return sqlSession.selectList("MenuMapper.selectAllMenus");
    }

    /* 7-5 */
    public MenuDTO selectMenuByMenuCode(SqlSession sqlSession, int menuCode) {

        // 사용자가 입력한 값 가지고 만드는 불완전한 쿼리
        return sqlSession.selectOne("MenuMapper.selectMenu", menuCode);
    }
}
