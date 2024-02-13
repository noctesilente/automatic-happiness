package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 이것도 @Component를 가지고 있음
public class BookService {

    /* 설명.
     *  아래의 세 가지는 ComponentScan 범위 안에 어노테이션들이 달렸을 때 유효하다.
     *  1. @Service에 의해서 BookService 타입의 bookService bean이 관리된다.
     *  2. BookDAOImpl에 있는 @Repository에 의해서 bookDAOImpl bean이 관리된다.(BookDAO 타입이기도 하다.)
     *  3. @Autowired에 의해서 BookDAO 타입의 빈이 BookService의 필드인 bookDAO 필드에 주입된다.(= 대입된다.)
     * */
    @Autowired
    private BookDAO bookDAO;
    // ComponentScan 범위 안에 BookDAO 타입의 bean이 대입됨
    // BookDAOImpl


    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    /* 설명. 도서 목록 전체 조회 */
    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
        // 해서 메소드 BookDAO에서 만들어주면 BookDAOImpl도 그 메소드를 오버라이딩해야 함
    }

}
