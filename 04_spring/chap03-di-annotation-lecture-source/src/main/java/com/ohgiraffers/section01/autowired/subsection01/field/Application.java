package com.ohgiraffers.section01.autowired.subsection01.field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        /* 설명.
         *  AnnotationConfigApplicationContext 생성자에 basePackages 문자열을 전달하여 ComponentScan
         *  개념을 따로 설정 클래스 없이 바로 적용할 수 있다.
        * */
        // 설정 파일 안 만들고 여기서 쓰기
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");
        // 컨테이너를 만들기 위해서는 ComponentScan만 있어도 만들 수 있음
        // ComponentScan 범위 지정 - section01 다 스캔
        // 어노테이션 붙은 것들 @Component 계열의 어노테이션이 붙은 것들을 찾음 - BookService, BookDAOImpl
        // 이 둘을 객체 생성
        // BookService는 Autowired가 달려있기 때문에 콩이 되려면 BookDAO 타입에 의존하고 있음 - 그게 생성이 되어야지만 BookService도 생성될 수 있음
        // = 의존성
        // 그래서 BookDAOImpl이 먼저 생성됨
        // 그리고 BookService 가 생성됨 그리고 밑에 private BookDAO bookDAO를 통해 주입된 후 기본생성자로 호출함
        // 콩을 주입받은 Service를 호출하면 service에 주입된 것이 가진 findallbook을 쓸 수 있음


        // 서비스 객체에 있는 메소드를 호출하려면 그 객체가 통으로 관리가 되고 있어야 함
        // 타입과 이름까지 다 호출하면서 해보기 -> 타입과 콩의 이름까지 다 써주기
        BookService bookService = context.getBean("bookService", BookService.class);
        // DAOImpl이 주입된 상태로 옴

        /* 설명. 전체 도서 목록 조회 후 출력 확인 */
        // ArrayList니까 forEach문 이렇게 써주기
        bookService.findAllBook().forEach(System.out::println);

        // 결과:
        // BookDTO(sequence=1, isbn=123456, title=자바의 정석, author=남궁성, publisher=도우출판, createdDate=Tue Feb 13 14:40:33 KST 2024)
        // BookDTO(sequence=2, isbn=222333, title=칭찬은 고래도 춤추게 한다, author=고래, publisher=고래출판, createdDate=Tue Feb 13 14:40:33 KST 2024)


        /* 설명. 도서 번호로 검색 후 출력 확인 */
        System.out.println(bookService.searchBookBySequence(1));
        System.out.println(bookService.searchBookBySequence(2));


    }
}
