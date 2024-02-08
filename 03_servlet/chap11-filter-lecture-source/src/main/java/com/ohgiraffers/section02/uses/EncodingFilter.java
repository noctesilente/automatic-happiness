package com.ohgiraffers.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

// 톰캣 10버전은 이걸 안 해도 됨!
@WebFilter("/member/*")
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // HttpServletRequest로 다운캐스팅 - 기존에 넘어올 때는 http 개념 없이 받음 - 그래서 http로 다운캐스팅을 해야 됨
        // get으로 넘어왔는지 post로 넘어왔는지 알기 위해서는 다운캐스팅을 해야 함
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        /* 설명. 우린 톰캣 10 버전인데 톰캣 10 버전은 기본 인코딩 설정이 UTF-8이라 안 해줘도 된다. */
        /* 설명. 요청이 POST 요청일 때는 UTF-8로 인코딩 설정을 사전 처리 해 주고 이후 필터나 서블릿으로 넘길 수 있다. */
        if ("POST".equals(httpRequest.getMethod())) {
            // httpRequest.getMethod().equals~ 로 왜 안 해주는지 = null pointer exception이 뜨지 않게 리터럴을 앞에 둠
            httpRequest.setCharacterEncoding("UTF-8");
        }

        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
