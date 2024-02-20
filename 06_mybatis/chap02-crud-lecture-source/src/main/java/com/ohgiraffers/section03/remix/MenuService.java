package com.ohgiraffers.section03.remix;

import java.util.List;

/* 설명.
 *  Service 계층 이후부터는 xml 방식, javaconfig 방식, remix 방식 중 어떤 것을 선택할지 고민해서 진행해야 한다.
 *  remix 방식의 경우는,
 *  mybatis 설정은 javaconfig 방식을 취하고, 쿼리를 다루는 것은 xml 방식을 취한다.
 *  DAO에 해당하는 것은 추상 메소드를 지닌 인터페이스로 만드는데 이 인터페이스와 mapper용 xml은 세 가지를 준수해야 한다.
* */
public class MenuService {
    public List<MenuDTO> findAllMenu() {
    }
}
