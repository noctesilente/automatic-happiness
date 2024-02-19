package com.ohgiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {
    // 설정과 쿼리가 있는 파일을 xml로 빼내는 것

    public static void main(String[] args) {

        String resource = "mybatis-config.xml";

        // xml 방식으로 하려면 스트림을 열고 읽어와야 함
        // 인풋스트림에 담아놓고 팩토리에 넣는 식으로 구현
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory
                    = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession(false);

            java.util.Date date = session.selectOne("mapper.selectNow");
            // selectNow만 쓰면 Mapper's namespace cannot be empty 가 뜸
            System.out.println("date = " + date);

            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
