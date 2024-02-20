package com.ohgiraffers.section03.remix;

import java.util.List;

/* 1-6 */
public class MenuController {

    private final MenuService menuService;
    private final PrintResult printResult;

    public MenuController() {
        this.menuService = new MenuService();
        this.printResult = new PrintResult();
    }


    public void findAllMenu() {

        List<MenuDTO> menus = menuService.findAllMenu();

        if (!menus.isEmpty()) {
            printResult.printMenus(menus);
        } else {
            printResult.printErrorMessage("전체 조회 실패!");
        }
    }
}
