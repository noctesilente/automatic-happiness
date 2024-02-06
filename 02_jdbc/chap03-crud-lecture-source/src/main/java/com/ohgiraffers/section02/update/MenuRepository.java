package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MenuRepository {
    public int modifyMenu(Connection con, Menu modifyMenu) {
        // insert, update, delete일 때는 반환형이 int여야 함
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/section01/insert/mapper/menu-mapper.xml"));
            // 확인해보기
//            System.out.println(prop.getProperty("updateMenu"));

            // 쿼리는 보기 좋게 String에 담아준 다음에 pstmt에 넣기
            String query = prop.getProperty("updateMenu");
            pstmt = con.prepareStatement(query);

            // 쿼리 보면서 순서 유의해서 작성하기
            pstmt.setString(1, modifyMenu.getMenuName());
            pstmt.setInt(2, modifyMenu.getMenuPrice());
            pstmt.setInt(3, modifyMenu.getMenuCode());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }
}
