package com.ohgiraffers.section01.xmlconfig;

import java.util.List;

/* 2-7 */
public class PrintResult {

    /* 6-3 */
    public void printMenus(List<MenuDTO> menuList) {
        menuList.forEach(System.out::println);
    }

    /* 6-4 */
    public void printErrorMessage(String message) {
        System.out.println("에러 메시지: " + message);
    }

    /* 7-8 */
    public void printMenu(MenuDTO menu) {
        System.out.println("menu = " + menu);
    }
}
