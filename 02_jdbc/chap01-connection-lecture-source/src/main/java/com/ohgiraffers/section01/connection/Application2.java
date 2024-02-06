package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application2 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        Connection con = null;

        // 파일로부터 읽어서 키와 밸류 4쌍이 properties에 쌓이게 됨
        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));
//            System.out.println("prop = " + prop);

            // 키로 불러와서 변수로 뽑겠다는 뜻
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password  = prop.getProperty("password");

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("con = " + con);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
        }
    }

    private static void close(Connection con) {
        try {
            if (con != null && !con.isClosed())
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
