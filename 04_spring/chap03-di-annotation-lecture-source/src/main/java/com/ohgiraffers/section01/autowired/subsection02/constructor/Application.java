package com.ohgiraffers.section01.autowired.subsection02.constructor;

import com.ohgiraffers.section01.autowired.subsection01.field.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        // subsection01 Application 그대로 복붙 후 수정

        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        /* 설명. subsection01에 있는 BookService 타입의 bean과 혼선을 방지하고자 bean 이름(id) 수정 */
        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);


    }
}
