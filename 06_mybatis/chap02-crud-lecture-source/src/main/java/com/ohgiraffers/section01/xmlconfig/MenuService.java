package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/* 4-4 */
import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;
// 이러면 메소드 이름만 가지고 쓸 수 있게 됨 = 원래는 클래스 명.메소드 명 식으로 써야 함

public class MenuService {

    /* 2-3 */
    private final MenuDAO menuDAO;

    // 의존성 주입을 우선 기본 생성자로 함
    public MenuService() {
        menuDAO = new MenuDAO();
    }

    /* 4-3 */
    public List<MenuDTO> findallMenus() {
        /* 4-5 */
        SqlSession sqlSession = getSqlSession();
        // DAO한테 넘겨주기
        List<MenuDTO> menuList = menuDAO.selectAllMenus(sqlSession);
        // 가져오든 안 가져오든 세션 닫기
        sqlSession.close();

        return menuList;
    }

    /* 7-4 */
    public MenuDTO findMenuByMenuCode(int menuCode) {
        SqlSession sqlSession = getSqlSession();

        // menuCode뿐만 아니라 sqlSession도 같이 넘겨줘야 함
        MenuDTO menu = menuDAO.selectMenuByMenuCode(sqlSession, menuCode);

        sqlSession.close();

        return menu;
    }

    /* 8-4 */
    public boolean registMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession, menu);

        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return (result > 0) ? true : false;
    }

    /* 9-5 */
    public boolean modifyMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, menu);

        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return (result > 0) ? true : false;
    }

    /* 10-4 */
    public boolean removeMenu(int menuCode) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, menuCode);

        if (result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return (result > 0) ? true : false;
    }
}
