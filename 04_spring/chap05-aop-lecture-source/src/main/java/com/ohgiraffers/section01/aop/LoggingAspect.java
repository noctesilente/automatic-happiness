package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {
    // 마찬가지로 bean 으로 관리가 되어야 하기 때문에 @Component 붙여주기

    /* 설명.
     *  타겟 클래스의 메소드에서 어드바이스를 적용할 수 있는 지점들을 조인포인트(joinpoint)라고 한다.
     *  포인트컷(pointcut)은 여러 조인포인트들에 어드바이스(advice)를 적용할 곳을 지정한 것이다.
     *  해당 조인포인트에서 어드바이스가 동작한다.
     *
     * 설명.
     *  <포인트컷 표현식>
     *  execution([수식어] 리턴타입 [클래스 이름].이름(파라미터))
     *  1. 수식어: public, private 등 수식어를 명시(생략 가능)
     *  2. 리턴 타입: 리턴 타입을 명시
     *  3. 클래스 이름(패키지명 포함) 및 메소드 이름: 클래스 이름과 메소드 이름을 명시
     *  4. 파라미터(매개변수): 메소드의 파라미터를 명시
     *  5. " * ": 1개이면서 모든 값이 올 수 있음
     *  6. " .. ": 0개 이상의 모든 값이 올 수 있음
     *
     * 설명.
     *  ex)
     *    execution(public Integer com.ohgiraffers.section01.advice.*.*(*))
     *    => com.ohgiraffers.section01.advice 패키지에 속해 있는 바로 다음 하위 클래스에 파라미터가 1개인 모든 메소드
     *       이자 접근 제어자가 public이고 반환형이 Integer인 경우
     *    execution(* com.ohgiraffers.section01.advice.annotation..stu*(..))
     *    => com.ohgiraffers.section01.advice 패키지 및 하위 패키지에 속해 있고 이름이 stu로 시작하는 파라미터가 0개
     *       이상인 모든 메소드이며 접근제어자와 반환형은 상관없음
     * */
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut() {

    }

    /* 설명. 1. Before Advice */
    //    @Before("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    // 지정한 위치 전마다 이 기능을 하게 하겠다는 뜻
    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        // 어떤 메소드라도 실행되기 전에 이 메소드가 실행되게 됨

        /* 설명.
         *  매개변수로 쓰인 JoinPoint
         *  : 포인트컷으로 패치된(지정된) 조인포인트
         *  (이해를 돕기 위해 써둔 거지 너무 신경 안 써도 된다고 하심)
         * */

//        System.out.println("Before Advice 동작");
        System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());

        // 타켓 메소드의 매개변수가 하나 이상이면
        if (joinPoint.getArgs().length > 0) {
            System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }

    }
    // 결과:
    // select all members일 때
    // Before joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@7c3fdb62
    // Before joinPoint.getSignature(): List com.ohgiraffers.section01.aop.MemberService.findAllMembers()
    // select one member일 때
    // Before joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@7c3fdb62
    // Before joinPoint.getSignature(): MemberDTO com.ohgiraffers.section01.aop.MemberService.findMemberBy(long)
    // Before joinPoint.getArgs()[0]: 1

    /* 설명. 2. After Advice */
    @After("LoggingAspect.logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
//        System.out.println("After Advice 동작");

        System.out.println("After joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());

        // 타켓 메소드의 매개변수가 하나 이상이면
        if (joinPoint.getArgs().length > 0) {
            System.out.println("After joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }
    }
    // 결과:
    // select all members일 때
    // After joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@7c3fdb62
    // After joinPoint.getSignature(): List com.ohgiraffers.section01.aop.MemberService.findAllMembers()
    // select one member일 때
    // After joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@7c3fdb62
    // After joinPoint.getSignature(): MemberDTO com.ohgiraffers.section01.aop.MemberService.findMemberBy(long)
    // After joinPoint.getArgs()[0]: 1


    /* 설명. 3. AfterReturning Advice */
    // 타켓으로 설정한 것이 반환한 것이 무엇인지 알 수 있음
    // 같은 클래스일 때는 메소드명만 써도 됨 - @AfterReturning 뒤에!! - Before이나 After도 마찬가지
    /* 설명. Pointcut에 해당하는 위치가 담긴 메소드가 같은 클래스에 있으면 클래스 소속을 안 적어도 된다. */
    /* 설명. returning에 쓰인 이름이 반환값이 넘어오는 매개변수 이름과 일치해야 한다.(result) */
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result: " + result);

        /* 설명. AfterReturning Advice를 활용한 반환값 가공도 가능하다. */
        // filter처럼 반환값 가로채서 가공한다고 생각하기
        if (result != null && result instanceof List) {
            // 전체 회원 조회를 했을 때의 타겟만 반환할 것
            ((List<MemberDTO>) result).add(new MemberDTO(3L, "반환 값 가공"));
        }
        // 주의! 반환된 값을 가공하는 것
    }
    // 결과:
    // find all members일 때
    // After Returning result: [MemberDTO(id=1, name=유관순), MemberDTO(id=2, name=홍길동)]
    // MemberDTO(id=3, name=반환 값 가공)
    // find one member일 때
    // After Returning result: MemberDTO(id=2, name=홍길동)

    /* 설명. 4. AfterThrowing Advice */
    // 타겟하는 것이 예외를 발생시켰을 때 동작하는 메소드
    /* 설명. throwing 속성 값과 매개변수 이름이 일치해야 한다.(exception) */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception: " + exception);
    }
    // 이대로 하면 예외가 발생하지 않기 떄문에 이게 실행이 안 됨 -> 예외 발생시키기
    // Application에 System.out.println(memberService.findMemberBy(2L)); 추가하고 실행
    // 결과: After Throwing exception: java.lang.IndexOutOfBoundsException: Index 2 out of bounds for length 2

    /* 설명. 5. Around Advice */
    /* 설명.
     *  이 어드바이스는 조인포인트를 완전히 장악하기 때문에 앞서 살펴본 어드바이스 모두
     *  Around 어드바이스로 조립할 수 있다.
     *  심지어 원본 조인포인트를 언제 실행할지, 실행 자체를 안 할지, 계속 실행할지 여부까지도 제어한다.
     *  AroundAdvice의 조인포인트 매개변수는 ProceedingJoinPoint로 고정되어 있다.
     *  JoinPoint의 하위 인터페이스로 원본 조인포인트의 진행 시점을 제어할 수 있다.
     *  조인포인트를 진행하는 호출을 잊는 경우가 자주 발생하기 때문에 주의해야 하며
     *  가능한 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는 게 바람직하다.
    * */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around Before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();        // 타겟 메소드 동작
        System.out.println("Around After: " + joinPoint.getSignature().getName());

        /* 설명. 실행된 타겟 메소드 반환(다른 어드바이스가 다시 실행할 수 있도록 반환한다.) */
        return result;
    }

    // 결과:
    // ========== select one member ==========
    // Around Before: findMemberBy
    // Before joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@4f4c4b1a
    // Before joinPoint.getSignature(): MemberDTO com.ohgiraffers.section01.aop.MemberService.findMemberBy(int)
    // Before joinPoint.getArgs()[0]: 1
    // target -> findAllMemberBy 실행
    // After Returning result: MemberDTO(id=2, name=홍길동)
    // After joinPoint.getTarget(): com.ohgiraffers.section01.aop.MemberService@4f4c4b1a
    // After joinPoint.getSignature(): MemberDTO com.ohgiraffers.section01.aop.MemberService.findMemberBy(int)
    // After joinPoint.getArgs()[0]: 1
    // Around After: findMemberBy

    // Around Before가 제일 처음 동작, Around After가 제일 마지막에 동작
    // 하지만 이렇게 여러 개를 많이 씌울 일은 없기 때문에 굳이 외우지는 말기
}
