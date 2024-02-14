package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404MessageKR = context.getMessage("error.404", null, Locale.KOREA);
        // getMessage로 하면 message를 알아서 찾음 - Locale.Korea면 message_ko_KR 파일을 알아서 찾아줌
        // 그래서 네이밍 규칙을 지켜서 properties 파일을 만들면 알아서 찾아줌
        String error500MessageKR
                = context.getMessage("error.500",
                new Object[]{"여러분", new java.util.Date()}, Locale.KOREA);

        System.out.println("I18N error.404 메시지: " + error404MessageKR);
        System.out.println("I18N error.500 메시지: " + error500MessageKR);
        // 결과:
        // I18N error.404 메시지: 페이지를 찾을 수 없습니다!
        // I18N error.500 메시지: 개발자의 잘못입니다. 개발자는 누구? 여러분입니다. 현재시간 24. 2. 14. 오후 2:26


        String error404MessageUS = context.getMessage("error.404", null, Locale.US);
        String error500MessageUS
                = context.getMessage("error.500",
                new Object[]{"여러분", new java.util.Date()}, Locale.US);

        System.out.println("I18N error.404 메시지: " + error404MessageUS);
        System.out.println("I18N error.500 메시지: " + error500MessageUS);
        // 결과:
        // I18N error.404 메시지: Page Not Found!!
        // I18N error.500 메시지: Something is wrong! The developers fault. Who is the developer? Its 여러분 at 2/14/24, 2:27 PM




    }
}
