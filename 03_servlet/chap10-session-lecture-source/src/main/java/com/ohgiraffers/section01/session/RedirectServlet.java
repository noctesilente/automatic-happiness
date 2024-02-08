package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    // url로 요청하면 무조건 get으로 요청함 = redirect는 무조건 항상 get으로 받음


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명. 리다이렉트로 넘어온 서블릿이더라도 같은 JSESSION 아이디가 넘어왔을 것이므로 기존과 같은 session 객체가 생성된다. */
        // 같은 JSESSIONID면 같은 사물함을 쓸 수 있음
        HttpSession session = req.getSession();
        // 만료 시간 뒤에 요청하면 사물함이 사라졌으니 기존에 없으니 아이디 전용 사물함이 다시 생성됨 = 기존의 사물함에 있던 값들을 유지가 안 됨

        // 같은 키 값 = firstName으로 꺼내기
        // Attribute는 꺼내면 Object형이기 때문에 다운캐스팅을 해줘야 함
        String firstName = (String) session.getAttribute("firstName");
        String lastName = (String) session.getAttribute("lastName");

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3>your first name is ")
                .append(firstName)
                .append("\n and last name is ")
                .append(lastName)
                .append("</h3>");

        // 한글로 쓰고 싶으면
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }
}
