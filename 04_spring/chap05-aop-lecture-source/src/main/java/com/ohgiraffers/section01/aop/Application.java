package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");
        // 여기서는 componentScan 기능도 쓸 것

        MemberService memberService = context.getBean("memberService", MemberService.class);
        System.out.println("========== select all members ==========");

        /* 설명. findAllMembers() 호출 이후 AfterReturning Advice가 회원 한 명을 추가하니 아래 예외 테스트 시에는 주석을 달 것 */
        List<MemberDTO> members = memberService.findAllMembers();
        members.forEach(System.out::println);

        // 결과:
        // ========== select all members ==========
        // Before Advice 동작
        // target -> findAllMembers 실행
        // After Advice 동작
        // MemberDTO(id=1, name=유관순)
        // MemberDTO(id=2, name=홍길동)

        System.out.println("========== select one member ==========");
        System.out.println(memberService.findMemberBy(1));
        // 결과:
        // ========== select one member ==========
        // Before Advice 동작
        // target -> findAllMemberBy 실행
        // After Advice 동작
        // MemberDTO(id=2, name=홍길동)

        /* 설명. 2번 인덱스에 해당하는 회원 조회(AfterThrowing Advice 확인용)(2번 인덱스 회원은 없음(회원이 2명이므로) */
//        System.out.println(memberService.findMemberBy(2));
    }
}
