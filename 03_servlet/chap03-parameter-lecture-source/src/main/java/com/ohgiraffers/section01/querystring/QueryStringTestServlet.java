package com.ohgiraffers.section01.querystring;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
// import가 안 되거나 여기서 에러가 생기면 톰캣에 문제가 있는 것

@WebServlet("/querystring")
public class QueryStringTestServlet extends HttpServlet{

    public QueryStringTestServlet() {
    }

    // ctrl O 누른 후 doGet만 오버라이딩
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request에서 key 값이 name인 걸 가져옴
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        // name이라는 key값으로 건져서 꺼냈더니 홍길동이 딸려옴

        // 홈페이지에서 이름만 쓰고 get 요청 보내면 여기에 name = 홍길동이 출력됨
        // 화면에서 넘어온 걸 꺼낼 수 있음

//        int age = req.getParameter("age");
        // 이러면 에러가 남 - 무조건 String 으로 반환하기 때문
        // wrapper 배운 걸 통해서 바꿔주기
        int age = Integer.valueOf(req.getParameter("age"));
        System.out.println("age = " + age);

        java.sql.Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
        System.out.println("birthday = " + birthday);

        String gender = req.getParameter("gender");
        System.out.println("gender = " + gender);

        String national = req.getParameter("national");
        System.out.println("national = " + national);

        String[] hobbies = req.getParameterValues("hobbies");
        System.out.println("hobbies = " + Arrays.toString(hobbies));

        // 톰캣 10버전이 된 이후로 GET과 POST 큰 차이 X
        // 결과: 홈페이지에서 입력된 대로 출력됨
        // name = 홍길동
        // age = 20
        // birthday = 2024-02-07
        // gender = M
        // national = ko
        // hobbies = [music]
    }
}
