package com.ohgiraffers.section01.xmlconfig;

import java.util.List;
import java.util.Map;

public class MenuController {
    // menuservice를 필요로 함

    /* 2-2 */
    // 반드시 이 객체를 주입받아야지 menucontroller가 존재할 수 있음
    private final MenuService menuService;

    /* 2-5 */
    /* 결과 페이지에 해당하는(View 개념의) 클래스 */
    // 지금 순수 자바 프로젝트라 html을 쓸 수 없고 뷰 리졸버가 없기 때문에
    // 클래스를 만들어서 결과 프로젝트처럼 쓰는 것
    private final PrintResult printResult;

    /* 2-6 */
    // 의존성 주입을 우선 기본 생성자로 함
    public MenuController() {
        menuService = new MenuService();
        printResult = new PrintResult();
    }


    /* 4-2. */
    public void findAllMenus() {

        List<MenuDTO> menuList = menuService.findallMenus();
        // 결과 제대로 넘어오는지 확인
//        System.out.println("menuList = " + menuList);

        /* 6-2 */
        if (!menuList.isEmpty()) {
            printResult.printMenus(menuList);
        } else {
            printResult.printErrorMessage("조회할 메뉴가 없습니다");
        }
    }

    /* 7-3 */
    public void findMenuByMenuCode(Map<String, String> parameter) {

        int menuCode = Integer.valueOf(parameter.get("menuCode"));
        // 자료형 변환 = 하나의 개념으로 묶기 위함

        MenuDTO menu = menuService.findMenuByMenuCode(menuCode);
//        System.out.println("menu = " + menu);

        /* 7-7 */
        if (menu != null) {
            printResult.printMenu(menu);
        } else {
            printResult.printErrorMessage(menuCode + "번의 메뉴는 없습니다.");
        }
    }

}
