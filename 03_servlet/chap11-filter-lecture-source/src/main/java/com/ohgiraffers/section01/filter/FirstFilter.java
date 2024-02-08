package com.ohgiraffers.section01.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/* 설명.
 *  WebFilter 어노테이션에 url 경로를 넣어 해당 요청 시 filter가 동작하게 한다.
 *  '/*'은 모든 요청 경로를 뜻한다.
* */
@WebFilter("/first/*")
public class FirstFilter implements Filter {

    // 기본생성자, init, doFilter, destory
    public FirstFilter() {
        System.out.println("FirstFilter 인스턴스 생성");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 객체가 생성되면 자동 호출되는 것
        System.out.println("FirstFilter init 호출");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter doFilter 호출");

        /* 설명. 다음 필터 또는 서블릿으로 가기 전에 전처리를 위한 코드를 여기에 작성 */

        /* 설명. FilterChain에서 제공하는 doFilter를 활용하여 다음 필터 또는 서블릿으로 진행시킬 수 있다. */
        // 그대로 다음 서블릿한테 전달 - dofilter = 다음 필터가 있다면 다음 필터로 가거나 필터가 없다면 서블릿으로 전달 = 문지기 역할
        filterChain.doFilter(servletRequest, servletResponse);

        /* 설명. 서블릿에서 클라이언트로 가기 전에 후처리를 위한 코드를 여기에 작성 */

        System.out.println("서블릿 다녀온 후");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy 호출");
    }
}
