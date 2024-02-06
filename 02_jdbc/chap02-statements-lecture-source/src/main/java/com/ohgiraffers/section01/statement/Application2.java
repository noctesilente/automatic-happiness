package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    /* 설명. 사번을 입력받아 한 명의 사원을 조회하는 기능 작성 */

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            /* 설명. 사용자로부터 조회하고자 하는 사원의 사번을 입력받음 */
            Scanner sc = new Scanner(System.in);
            System.out.print("사번을 입력하세요: ");
            int empId = sc.nextInt();

            String entYn = "N";

            /* 설명. 입력받은 사번을 활용한 쿼리 작성 */
            // 트럭을 통해서 갈 쿼리 만들기
            // 자동형변환을 고려해서 문자열이 되도록 쿼리를 만들기 위해 ''를 붙여줌
            // EMP_ID는 넘버형이 아니라 VARCHAR형이기 때문
//            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            // 하나 더 추가하려면? -> 쿼리가 굉장히 지저분해지기 때문에 section02.prepared.Application2처럼 해주기
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId
                    + "' AND ENT_YN = '" + entYn + "'";

            System.out.println("query = " + query);

            rset = stmt.executeQuery(query);

            // 다중행이 아니기 때문에 if 조건문 사용
            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID")
                        + ", " + rset.getString("EMP_NAME"));
            } else {
                System.out.println("해당 사원의 조회 결과가 없습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
