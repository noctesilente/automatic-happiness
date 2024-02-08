package com.ohgiraffers.section02.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        System.out.println("attribute 만료 전 firstName: " + session.getAttribute("firstName"));
        System.out.println("attribute 만료 전 lastName: " + session.getAttribute("lastName"));

        // 접속한 사용자 전용 사물함을 무효화함
        // HttpSession 객체의 Attribute 값들을 무효화 = 공간을 뭉개버렸다고 생각하기
        // 로그아웃인 것
        // session은 존재하고 attribute를 다시 담을 수 있긴 함
        session.invalidate();
        // session 틀만 있고 안은 텅 비어버린 것

        System.out.println("attribute 만료 후 firstName: " + session.getAttribute("firstName"));
        System.out.println("attribute 만료 후 lastName: " + session.getAttribute("lastName"));

        /* 설명. session이 무효화되어 attribute를 가져올 수 없게 되고 500 에러 발생 */
        // attribute 만료 전 값들은 출력이 제대로 되지만 후 값들은 아예 출력이 되지 않음

    }
}
