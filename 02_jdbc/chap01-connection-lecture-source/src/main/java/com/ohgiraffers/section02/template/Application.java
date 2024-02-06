package com.ohgiraffers.section02.template;

import java.sql.Connection;

/* 설명. import 뒤에 static을 붙이고 메소드 명까지 쓰게 되면 다른 클래스의 static 메소드를 마치 내 class에 있는 것처럼 사용할 수 있다. */
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;
import static com.ohgiraffers.section02.template.JDBCTemplate.close;
// 클래스 명까지가 아니라 메소드 명까지 적으면 마치 이 클래스에서 이 메소드가 있는 것처럼 쓸 수 있음

public class Application {
    public static void main(String[] args) {
        // 원래 다른 클래스에 있는 메소드니까 앞에 다른 클래스명. 을 붙여줘야 함
//        Connection con = JDBCTemplate.getConnection();
        // import static method 해주면 위처럼 안 써도 됨
        // 어디서든 이 Connection 객체가 필요하면 이제 이런 식으로 해줄 수 있음
        Connection con = getConnection();

        /* 설명. 비즈니스 로직(트랜잭션 처리) 실행 */
        // 이 공간 안에서 하나라도 실패하면 다 실패
        // 연결점을 찾고 연결점을 통해서 할 일을 함 -> 사용자가 앞에서 넘겨준 값을 가지고 쿼리를 만드는 일을 하고 그 쿼리를 가지고 판단하는 일을 함
        System.out.println("con = " + con);


        // 이러고 끝내면 안 되고 닫아줘야 함
        // close 메소드를 JDBCTemplate 에 만들어줌
        close(con);
    }
}
