package com.ohgiraffers.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        // 웹페이지에 입력한 것 출력됨
        // 결과:
        // firstName = 카츄
        // lastName = 피

        /* 설명.
         *  쿠키(클라이언트의 브라우저에 저장)를 생성하고 사용하는 방법
         *   1. 쿠키 인스턴스 생성한다.(백엔드 측에서는 쿠키도 결국 객체)
         *   2. 해당 쿠키의 만료 시간을 설정한다.
         *   3. 응답 헤더(response header)에 쿠키를 담는다.
         *   4. 응답한다.(호출했던 곳으로 다시 돌아가면 됨)
        * */

        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);

        // 초 단위로 설정
        // 하루 - 24시간을 만료 시간으로 하고 싶다면
        firstNameCookie.setMaxAge(60 * 60 * 24);
        // 12시간을 만료 시간으로 하기
        lastNameCookie.setMaxAge(60 * 60 * 12);

        resp.addCookie(firstNameCookie);
        resp.addCookie(lastNameCookie);

        // redirect하기 - 사용자한테 돌아가서 재요청
        resp.sendRedirect("redirect");

        // 일단 값들이 다 쿠키 안에 들어간 걸 알 수 있음 - 사이트에서 F12 눌러서 확인
    }
}
