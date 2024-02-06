package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 수업목표. 해당 DBMS와 계정에 맞는 Connection 객체를 생성할 수 있다.(feat. DBMS Driver 추가)  */
// Connection 객체가 생성이 되면 통로가 생성된 것이라고 보면 됨
public class Application1 {
    public static void main(String[] args) {

        // connection을 만들어볼 것
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "swcamp", "swcamp");

            System.out.println("con = " + con);
            // dbms랑 연동하고 쿼리로 처리
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null && !con.isClosed())
                    con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
