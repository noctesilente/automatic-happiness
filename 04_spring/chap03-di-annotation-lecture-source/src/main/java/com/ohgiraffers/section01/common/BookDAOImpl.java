package com.ohgiraffers.section01.common;

import org.springframework.stereotype.Repository;

import java.lang.reflect.AnnotatedType;
import java.util.*;

@Repository
public class BookDAOImpl implements BookDAO{

    // 인터페이스를 구현하는 클래스

    // 나누는 이유 - 실제 구현되는 IMPL을 숨기는 것
    // 실제로 어떤 빈이 주입되는지를 숨기는 기술 - PSA = portable service abstraction
    // 뒤에 계층 하나를 숨겨두는 것
    // 일종의 추상화 기술이자 다형성 적용 - 다형성은 다른 말로 하면 은닉화 기술
    // 이러한 건 타입 은닉
    // 쓰는 이유: 유지 보수와 확장성 때문
    // 이거 말고 다른 impl bean이 생성되더라도 서비스에서는 그걸 모름 서비스는 BookDAO만 알고 있음

    private Map<Integer, BookDTO> bookList;

    public BookDAOImpl() {
        bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1, 123456, "자바의 정석"
                , "남궁성", "도우출판", new Date()));
        bookList.put(2, new BookDTO(2, 222333, "칭찬은 고래도 춤추게 한다"
                , "고래", "고래출판", new Date()));
    }

    @Override
    public List<BookDTO> findAllBook() {

        /* 설명. HashMap의 value들만 뽑아 ArrayList 형태로 반환 */
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO searchBookBySequence(int sequence) {
        // 키 값이 sequence
        return bookList.get(sequence);
    }

}
