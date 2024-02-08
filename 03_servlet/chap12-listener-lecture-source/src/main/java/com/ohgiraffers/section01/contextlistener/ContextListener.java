package com.ohgiraffers.section01.contextlistener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {
    // interface 두 개를 가져옴 = 각각의 기능을 다 보기 위해서 그냥 두 개 가져옴


    public ContextListener() {

        /* 설명. context가 생성될 때 변화를 감지하는 Listener 인스턴스가 함께 생성된다. */
        System.out.println("context listener 인스턴스 생성");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {

        /* 설명. context에 attribute가 추가될 때 동작한다. */
        System.out.println("context attribute added!");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {

        /* 설명. context에 attribute가 제거될 때 동작한다. */
        System.out.println("context attribute removed!");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {

        /* 설명. context에 attribute가 변경될 때 동작한다. */
        System.out.println("context attribute replaced!");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        /* 설명. context가 생성될 때 생성자 호출 이후 동작하는 메소드이다. */
        System.out.println("context init!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        /* 설명. context가 소멸될 때 동작하는 메소드이다. */
        System.out.println("context destroyed!!");
    }

    // context 서블릿 여러 개고 listener가 하나라고 해도 다 적용이 됨 = 대신 패키지 하위에 있는 것만!
    // 다 들을 수 있다!
}
