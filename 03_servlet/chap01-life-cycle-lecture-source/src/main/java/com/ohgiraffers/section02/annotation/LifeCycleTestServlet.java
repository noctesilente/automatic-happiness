package com.ohgiraffers.section02.annotation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(value="/annotation-lifecycle")
public class LifeCycleTestServlet extends HttpServlet {

    // 기본 생성자 만들기
    public LifeCycleTestServlet() {
        System.out.println("annotation 기본 생성자 실행");
    }

    // ctrl + O 누르면 부모로부터 물려받는 모든 메소드가 다 뜸
    // 선택하면 굳이 오버라이딩 할 필요 없이 뜸

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("annotation 매핑 init() 메소드 호출");
    }

    @Override
    public void destroy() {
        System.out.println("annotation 매핑 destroy() 메소드 호출");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("annotation 매핑 service() 메소드 호출");
    }
}
