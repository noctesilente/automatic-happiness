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

    /* 8-5 */
    public int insertMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.insert("MenuMapper.insertMenu", menu);
    }

    /* 9-6 */
    public int updateMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.update("MenuMapper.updateMenu", menu);
    }

    /* 10-5 */
    public int deleteMenu(SqlSession sqlSession, int menuCode) {

        return sqlSession.delete("MenuMapper.deleteMenu", menuCode);
    }
}
