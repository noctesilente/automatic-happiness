package com.ohgiraffers.mybatisspring.section01.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MenuServiceTests {
    // 테스트 하려는 서비스랑 같은 계층에 만들어야 함
    // 임포트 없이 쓸 수 있음

    @Autowired
    // 테스트를 할 거면 생성자 주입 안 받아도 됨
    private MenuService menuService;

    @DisplayName("주문 가능 상태별 메뉴 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    // ValueSource = 기본값 넣어줄 수 있음
    void testFindAllMenus(String orderableStatus) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MenuDTO> menus = menuService.findAllMenuByOrderableStatus(orderableStatus);
                    // 테스트 하고 출력까지 해보기
                    menus.forEach(System.out::println);
                }
        );
    }
}
