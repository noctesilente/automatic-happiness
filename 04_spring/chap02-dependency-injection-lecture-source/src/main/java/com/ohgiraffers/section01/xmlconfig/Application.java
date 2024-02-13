package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context
                = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

        // 타입이 하나밖에 없으니 이렇게 뽑아도 하나만 나옴
        MemberDTO member = context.getBean(MemberDTO.class);

        /* 설명. 컨테이너가 관리 중인 MemberDTO 타입의 bean에 은행코드 20번인 PersonalAccount 객체가 주입되어 있음을 확인 */
        // personalAccount가 있는지 확인 - 주입받은 콩 확인
        System.out.println(member.getPersonalAccount());
        // 결과: PersonalAccount{bankCode=20, accNo='110-234-45657890', balance=0}

        /* 설명. member bean이 가진 계좌 객체의 기능을 활용해 입금 및 출금을 진행해 보자. */
        // 콩이 가진 계좌의 기능들을 활용할 수 있음
        System.out.println(member.getPersonalAccount().deposit(10000));
        System.out.println(member.getPersonalAccount().getBalance());
        System.out.println(member.getPersonalAccount().withdraw(5000));
        System.out.println(member.getPersonalAccount().getBalance());

        // 결과:
        // 10000원이 입금되었습니다.
        // 110-234-45657890계좌의 현재 잔액은 10000원입니다.
        // 5000원이 출금되었습니다.
        // 110-234-45657890계좌의 현재 잔액은 5000원입니다.
    }
}
