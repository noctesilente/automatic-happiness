package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        MemberDTO member = context.getBean(MemberDTO.class);

        System.out.println(member.getPersonalAccount());
        // 결과: PersonalAccount{bankCode=20, accNo='110-234-5678', balance=0}

        System.out.println(member.getPersonalAccount().deposit(20000));
        System.out.println(member.getPersonalAccount().getBalance());
        System.out.println(member.getPersonalAccount().withdraw(7000));
        System.out.println(member.getPersonalAccount().getBalance());

        // 결과:
        // 20000원이 입금되었습니다.
        // 110-234-5678계좌의 현재 잔액은 20000원입니다.
        // 7000원이 출금되었습니다.
        // 110-234-5678계좌의 현재 잔액은 13000원입니다.
    }
}
