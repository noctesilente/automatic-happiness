package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 6-1 */
/* 설명. Interceptor 추가 및 static(정적) 리소스 호출 경로 등록 설정 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    // 핸들러 메소드를 거치지 않고 static 에 들어있는 파일만 쏙 뽑아서 사용 가능
    // static 영역에 있는 파일만 가져가도록 하는 것

    /* 6-2 */
    // StopwatchInterceptor도 빈이기 때문에 여기서 가져올 수 있다
    private StopwatchInterceptor stopwatchInterceptor;

    /* 6-3 */
    // 생성자 받기 + Autowired로 객체까지 주입 받기
    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }

    /* 6-4 */
    /* 설명. interceptor를 따로 여기서 등록해 주어야 실제로 동작하는 interceptor가 된다. */
    // ctrl+O 사용해서 addInterceptors 사용
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopwatchInterceptor)
                // 이건 12번!
                .excludePathPatterns("/css/**");        // excludePathPatterns를 등록해준 경로의 요청은 인터셉터가 가로채지 않음
        // 이렇게 우리의 interceptor를 등록해줘야지 사용을 할 수 있음!
    }
    // 등록하고 실행하면
    // 결과:
    // preHandle 호출함... (핸들러 메소드 이전)
    // 핸들러 메소드 호출함...
    // postHandle 호출함... (핸들러 메소드 이후)
}
