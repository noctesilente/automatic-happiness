package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 매개변수가 달라도 close라는 메소드를 호출하겠다는 건 똑같아서 그냥 이거 하나만 임포트 해주면 됨
import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /* 설명. 트랜잭션 처리르 위한 DBMS 연동용 Connection 객체 생성 */
        Connection con = getConnection();

        /* 설명. 해당 Connection을 통해 트랜잭션 처리(비즈니스 로직 수행, CRUD) */
        System.out.println("con = " + con);

        // 퀴리를 운반하고 결과를 반환하는 객체
        Statement stmt = null;
        // 조회의 결과 반환되는 객체
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            /* 설명. 쿼리의 결과인 다중행/단일행을 받은 ResultSet(현재는 다중행) */
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE");

            /* 설명. rset.next() = 한 행씩 확인 */
            while (rset.next()) {                   // 이 반복문의 조회의 결과 행만큼 반복

                /* 설명. 반복문 안에서의 rset은 단일행으로 해석할 것 */
                System.out.println(rset.getString("EMP_NAME")
                        + ", " + rset.getInt("SALARY"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            // JDBCTemplate에 rset이랑 stmt, con 추가해줌
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
