package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 처음에 쿼리와 함께 만들어짐
        /* 설명. Statement와 달리 PreparedStatement는 생성 당시에 쿼리가 있어야 한다. */
        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID"
                        + ", " + rset.getString("EMP_NAME")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            // PreparedStatement용 close를 따로 만들 필요 없음- 다형성 때문에 그대로 사용 가능
            close(pstmt);
            close(con);
        }

    }
}
