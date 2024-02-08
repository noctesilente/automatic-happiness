package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        // 웹페이지에서 입력한 걸 그대로 받아옴
        // 결과:
        // firstName = 길동
        // lastName = 홍

        /* 설명.
         *  요청이 서블릿으로 들어올 때 HttpServletRequest에는 JSESSIONID라는 값이 Header에 같이
         *  포함되어 넘어온다. 그럼 해당 JSESSIONID에 맞는 HttpSession 객체를 생성해서 해당 사용자를
         *  위한 전용 저장 공간을 제공하게 된다.
         *  (요청을 보낸 사람 전용 Session 저장 공간을 생성하게 된다.)
        * */
        HttpSession session = req.getSession();
        // 왜 request에서 getSession이 가능한지
        // = request header로 jsession id가 넘어옴

        /* 설명. 따로 설정하지 않으면 session 공간은 30분의 만료 시간을 가진다. */
        // 처음 만들면 기본적으로 30분의 유지 시간을 가짐
        System.out.println("session 객체의 default 유지 시간(초): " + session.getMaxInactiveInterval());
        // 결과: session 객체의 default 유지 시간(초): 1800

        // 시간을 줄이고 싶다면
        session.setMaxInactiveInterval(60 * 10);            // (초 단위) 10분
        System.out.println("session 객체의 default 유지 시간(초): " + session.getMaxInactiveInterval());
        // 결과: session 객체의 default 유지 시간(초): 600

        // JSESSIONID 출력
        System.out.println("session id: " + session.getId());
        // 결과: session id: 56D496A390BF062D33BE254E78D9BDEB


        // 사물함에 값을 어떻게 담는가?
        // setAttribute로 key와 value 형태로 담음
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        // request와 session의 차이점:
        // request는 요청이 끝나면 저장 공간이 사라지지만
        // session은 만료 시간까지 쭉 유지함 = 키를 들고 오면 언제든지 사용 가능

        // session 기능 확인 위해 redirect 해보기
        resp.sendRedirect("redirect");
    }
}
