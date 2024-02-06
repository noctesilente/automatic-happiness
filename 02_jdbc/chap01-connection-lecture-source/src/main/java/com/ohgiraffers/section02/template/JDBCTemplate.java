package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* 설명. JDBC를 위한 메소드만 따로 모듈화(Connection 객체 생성, close 메소드 처리(Connection, Statement, PreparedStatement) */
public class JDBCTemplate {
    // 부르면 Connection을 만들어주는 메소드
    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            /* 설명. 이번에는 'user'라는 키와 'password'라는 키 값을 지닌 properties 객체를 넘겨주고 Connection 객체 생성 */
            // 그래서 키 값은 오타를 내면 안 됨 - 자동으로 찾아주는 거기 때문
            // 일종의 통신할 수 있는 도로를 두 가지를 가지고 뚫었다고 생각해주면 됨
            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 이번에는 close 메소드를 여기서 호출하지 않는다.(해당 DBMS와 연결할 수 잇는 Connection 반환만 해당되게 작성) */
        // close 안 하고 Connection 반환을 해주면 이 도로를 다 활용한 후에 Close 메소드를 따로 만들어줄 것 - 연결하는 역할만 함
        // 연결하고 바로 닫아주면 안 되니까 close 메소드를 따로 만들어줌

        return con;
    }

    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed())
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
