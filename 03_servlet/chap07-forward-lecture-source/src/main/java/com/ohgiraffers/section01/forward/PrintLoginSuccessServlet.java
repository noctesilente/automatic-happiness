package com.ohgiraffers.section01.forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/print")
public class PrintLoginSuccessServlet extends HttpServlet {
    // get 요청을 했는데 포워딩을 하면 get으로 가고
    // 처음에 doPost였는데 포워딩을 하면 doPost로 감

    public PrintLoginSuccessServlet() {
    }

    /* 설명. 이전 서블릿이 doPost로 처리하다가 포워딩 되면 doPost로 받아야 한다.(doGet은 doGet으로 처리) */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("==== 포워딩 된 서블릿에서 넘겨받은 request 객체에 담긴 값 확인 ====");
        System.out.println("이름: " + req.getAttribute("userName"));
        System.out.println("아이디: " + req.getParameter("userId"));
        System.out.println("패스워드: " + req.getParameter("password"));

        // 결과:
        // 이름: 홍길동
        // 아이디: pikachu
        // 패스워드: pika

        // 새로고침을 누르면 계속 요청이 날아감
        // 계속 첫번째 서블릿의 db를 바꾸는 것 -> 홍길동을 전달받음

        String userName = (String) req.getAttribute("userName");

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3 align=\"center\">\n")
                .append(userName)
                .append("님 환영합니다.</h3>");
        // 부가적인 태그를 안 만들어도 웹페이지가 만들어지긴 함

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }
}
