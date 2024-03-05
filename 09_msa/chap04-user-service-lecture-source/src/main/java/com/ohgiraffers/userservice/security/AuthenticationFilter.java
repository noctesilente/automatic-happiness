package com.ohgiraffers.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.userservice.service.UserService;
import com.ohgiraffers.userservice.vo.RequestLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment environment) {
        super(authenticationManager);
        this.userService = userService;
        this.environment = environment;
    }

    /* 설명. 로그인 시도 시 동작하는 기능(POST 방식의 /login 요청) -> request body에 담겨 온다.(stream 활용) */
    /* 설명. service 계층 손보러 가자(우리의 Service 클래스를 UserDetailService로 만들기(Service interface로 이동(Service에서 받으면 impl도 영향을 받음)) */
    // interface에서 extends userdetailService 붙여주기
    // impl 클래스에서 alt enter -> implement metohd 해주기

    // ctrl+O를 통해 오버라이딩
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            RequestLogin requestLogin = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getEmail(), requestLogin.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // RequestLogin = 우리가 만들어야 되는 VO 타입
    }




    /* 설명. 로그인 성공 시 JWT 토큰 생성하는 기능 */

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 그냥 간단하게 출력
        System.out.println("authResult = " + authResult);
    }

    // postman에서 post 로 port 뒤에 login 붙여주고 확인해보기
    // 껐다 켰기 때문에 db가 없어서 registuser 먼저 시켜보기
    // 하고 login 눌러보기
}
