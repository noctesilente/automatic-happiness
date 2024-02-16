package com.ohgiraffers.exceptionhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class RootConfiguration {
    // 여기에 다 작성해놓으면
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties props = new Properties();
        props.setProperty("java.lang.NullPointerException", "error/nullPointer");
        // NullPointerException이 발생하면 error 파일 밑에 있는 nullPointer.html로 가라는 뜻

        /* 설명. 전체 예외 관련되어 SimpleMappingExceptionResolver에 설정하기 */

        /* 설명. 1. 예외에 따른 페이지 설정한 것 활용 */
        exceptionResolver.setExceptionMappings(props);
        // 어떤 예외가 어디로 가야 되는지 인식을 하게 됨

        /* 설명. 2. 그 외 나머지 예외에 대한 default 페이지 설정 */
        exceptionResolver.setDefaultErrorView("error/default");
        // 세팅하지 않은 예외가 발생하면 어디로 가야 할지 세팅

        /* 설명. 3. 예외 메시지를 뽑기 위한 Attribute key 값(화면에서 예외 메시지 확인 시 사용할 값) */
        exceptionResolver.setExceptionAttribute("exceptionMessage");
        // 메시지는 어떤 키 값으로 뽑을지도 세팅

        return exceptionResolver;
    }
}
