package com.ohgiraffers.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/member/*")
public class PasswordEncryptFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("패스워드 암호화 필터의 doFilter 실행!");
//        filterChain.doFilter(servletRequest, servletResponse);

        // 결과:
        // 패스워드 암호화 필터의 doFilter 실행!
        // userID = user01
        // password = pass01
        // name = 홍길동

        // 이런 식으로 다 나오면 큰일
        // servlet에 닿기 전 filter에서 암호화를 해야 함 - 방식이 정해져 있음(법으로)
        // 암호를 하는 건 되는데 복호화는 안 되게 막아야 함 = 단방향 암호화
        // hash 알고리즘을 활용해야 함

        /* 설명. 기존의 request 객체에서 우리가 재정의한 request 객체로 갈아끼움 */
        // RequestWrapper에서 정의한 필터를 갈아끼움 - 한 줄 추가
        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(wrapper, servletResponse);

        // 실행하면 비밀번호가 암호화한 채로 나옴
        // 결과:
        // 패스워드 암호화 필터의 doFilter 실행!
        // userID = user01
        // password = $2a$10$1lRsnkecQhT5nTCPVoOT5OCUbGvz/liBTCwaj22UhUtHrgvv8aEpW
        // name = 홍길동
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
