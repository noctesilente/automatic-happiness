package com.ohgiraffers.section01.problem;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemOfUsingDirectSQLTests {
    // sql을 직접 써서 생기는 문제들

    // 모든 테스트 코드는 db랑 연결하는 사전 작업 필요

    private Connection con;

    @BeforeEach
    void setConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menudb";
        String user = "swcamp";
        String password = "swcamp";

        Class.forName(driver);

        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        // test만 하고 db에 적용 X
        con.rollback();
        con.close();
    }

    @DisplayName("직접 SQL을 작성하여 메뉴를 조회할 때 발생하는 문제 확인")
    @Test
    void testDirectSelectSql() throws SQLException {

        // given
        String query = "SELECT MENU_CODE, MENU_NAME, MENU_PRICE, CATEGORY_CODE, "
                + "ORDERABLE_STATUS FROM TBL_MENU";

        // when
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        // 다중행 조회는 while을 돌림 - while을 돌려서 llist에 쌓기
        // 메뉴라는 엔티티에 담을 것
        List<Menu> menuList = new ArrayList<>();
        while (rset.next()) {
            Menu menu = new Menu();
            menu.setMenuCode(rset.getInt("MENU_CODE"));
            menu.setMenuName(rset.getString("MENU_NAME"));
            menu.setMenuPrice(rset.getInt("MENU_PRICE"));
            menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
            menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));

            menuList.add(menu);
        }

        // then
        // 비어 있지 않으면 초록불
        Assertions.assertTrue(!menuList.isEmpty());
        menuList.forEach(System.out::println);

        rset.close();
        stmt.close();
    }

    // 이렇게 하다는 것이 불편하다는 것을 알면 됨

    @DisplayName("직접 SQL을 작성하여 신규 메뉴를 추가할 때 발생하는 문제 확인")
    @Test
    void testDirectInsertSQL() throws SQLException {

        // given
        Menu menu = new Menu();
        menu.setMenuName("민트초코짜장면");
        menu.setMenuPrice(12000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        String query = "INSERT INTO TBL_MENU(MENU_NAME, MENU_PRICE, CATEGORY_CODE, " +
                "ORDERABLE_STATUS) VALUES (?, ?, ?, ?)";

        // when
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, menu.getMenuName());
        pstmt.setInt(2, menu.getMenuPrice());
        pstmt.setInt(3, menu.getCategoryCode());
        pstmt.setString(4, menu.getOrderableStatus());

        int result = pstmt.executeUpdate();

        // then
        Assertions.assertEquals(1, result);
        pstmt.close();
    }
    // 얼마나 귀찮은지만 알아보기
    // 매번 쿼리 보낼 때마다 pstmt인 트럭을 만들어주고 ?에 맞는 자료형까지 맞춰주는 것이 번거로움


}
