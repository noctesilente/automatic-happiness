package com.ohgiraffers.userservice.security;

import com.ohgiraffers.userservice.service.UserService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    // 의존성 주입
    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    Environment env;

    @Autowired
    public WebSecurity(UserService userService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       Environment env) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

    /* 설명. 인가(Authorization)용 메소드 */
    // post용으로 /users 하게 되면 인가해주라고 해줘야 함
    // 식별이 되고 나서도 이 사람한테 권한을 줄 건지를 구분하는 것이 인가
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        /* 설명. 로그인 시 추가할 내용 */
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        /* 설명. JWT 로그인 처리를 할 것이므로 csrf는 신경 쓸 필요가 없다. */
        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(new AntPathRequestMatcher("/health_check")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll())
                .authenticationManager(authenticationManager);

        http.addFilter(getAuthenticationFilter(authenticationManager));

        return http.build();

    }


    /* 설명. 인증(Authentication)용 메소드 */
    // 사용자가 로그인을 할 때 사용자가 맞는지 식별을 하는 것이 인증
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, userService, env);
    }





}
