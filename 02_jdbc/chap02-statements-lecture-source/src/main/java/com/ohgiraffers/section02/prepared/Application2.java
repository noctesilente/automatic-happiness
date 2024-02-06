package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

/* 설명.
 *  PreparedStatement는 Statement와 달리 Placeholder(?)를 사용한 쿼리를 파싱하고 캐싱하여 다시 재해석 하는 과정을 생략함으로
 *  인해 불완전하게 작성된 쿼리 실행의 경우 Statement보다 빠르다.
 * */
public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사번을 입력하세요: ");
        String empId = sc.nextLine();

        String entYn = "N";

        /* 설명. PreparedStatement는 Statement 때와 달리 placeholder(?)를 활용한 하나의 문자열 형태로 쿼리 작성 가능 */
        // 물음표 자리에 뭐가 들어오느냐에 따라서 결과가 완전히 달라지는 불완전한 쿼리
        // 사용자의 입력이 들어갈 부분을 물음표 형태로 표기하고 placeholder라고 함
        // 하나의 깔끔한 문자열 형태로 쿼리를 만들 수 있음
//        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";

        // 두 개를 알고 싶으면
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? AND ENT_YN = ?";

        // 아예 처음부터 숫자를 넣을 거면 '201'로 넣어야 함!
//        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '201'";

        try {
            pstmt = con.prepareStatement(query);
            // 앞에 있는 물음표를 메꾸고 출발 -> 첫번째 물음표에 empId를 넣으라는 뜻
            // 물음표가 두 개 이상이면 2, 3, 이런 식으로 설정하고 넣을 값을 뒤에 써주면 됨
            // set 써주면 String뿐만 아니라 다양한 자료형이 나옴
            pstmt.setString(1, empId);
            pstmt.setString(2, entYn);

            rset = pstmt.executeQuery();

            // 이 쿼리로는 단일행만 나오기 때문에 if조건문 사용
            // 다중행일 때 if 쓰면 행 하나만 가져오는 것
            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID")
                        + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
