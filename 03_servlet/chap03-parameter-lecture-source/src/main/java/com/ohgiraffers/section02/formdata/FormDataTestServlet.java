package com.ohgiraffers.section02.formdata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/* 설명. form 태그의 method="post"로 post 요청으로 넘어온 데이터(parameter) 꺼내보기 */
@WebServlet("/formdata")
public class FormDataTestServlet extends HttpServlet {

    // 기본생성자
    public FormDataTestServlet() {
    }

    // CTRL O => do post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명. tomcat 9버전 이하는 post 요청일 때 한글이 깨지지 않으려면 UTF-8 인코딩 설정을 해 주어야 한다. - 지금은 안 해도 됨 */
//        req.setCharacterEncoding("UTF-8");

        /* 설명. 키 값을 통해 파라미터들을 하나씩 꺼내는 과정은 get 방식과 동일하다. */
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        // 사이트에서 입력한 대로 출력됨
        // 결과: name = 유관순


        /* 설명. 요청으로 넘어오는 파라미터들의 키 값들을 한 번에 확인해 보기 */
        // Enumeration = iterator 구버전이라고 생각하기
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }
        // 키 값이 뭐가 넘어오는지를 일일이 보기보다는 이렇게 확인 가능
        // 결과:
        // name
        // age
        // birthday
        // gender
        // national
        // hobbies

    }
}
