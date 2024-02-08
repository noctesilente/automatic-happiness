package com.ohgiraffers.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 웹 브라우저에 있는 쿠키가 전부 request header
        // 쿠키가 있는 상태에서 요청을 하면 무조건 쿠키가 같이 옴

        /* 설명.
         *  쿠키를 불러오는 방법
         *  1. request header에서 쿠키 목록을 쿠키 배열 형태로 꺼내온다.
         *  2. 쿠키의 getName()과 getValue()를 이용해 쿠키에 담긴 키와 밸류를 사용한다.1
        * */
        // 쿠키 한 번에 확인하기
        Cookie[] cookies = req.getCookies();

        String firstName = "";
        String lastName = "";
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());

            if ("firstName".equals(cookie.getName())) {
                firstName = cookie.getValue();
            } else if ("lastName".equals(cookie.getName())) {
                // 다른 쿠키도 넘어오기 때문에 else가 아닌 else if로 해주어야 함
                lastName = cookie.getValue();
            }
        }

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3>당신의 이름은 ")
                .append(firstName)
                .append("<br>그리고 성은 ")
                .append(lastName)
                .append("</h3>");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();

        // 서버는 쿠키들을 만료 시간이 되기 전까지 항상 받을 수 있음 - 써주지 않아도 원래 있던 쿠키도 넘어감
        // 결과:
        // JSESSIONID: 32EE2B96A9C30496F837CDC9585ECB4C
        // firstName: 상해씨
        // lastName: 이
    }
}
