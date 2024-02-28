package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/menu")
@Slf4j              // log 찍는 용도로라도 집어넣기
public class MenuController {

    private final MenuService menuService;


    /* 설명. 로그 생성해 보기 */
    // log 찍기 - 1. lombok 2. logger
//    Logger logger = LoggerFactory.getLogger(MenuController.class);
    // 더 쉬운 방법
//    Logger logger = LoggerFactory.getLogger(getClass());

    // lombok 사용
    // 클래스 위에 @Slf4j 붙이기


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuCode}")          // path valuable 방식 - 경로 상에 있는 걸 변수로 받아내는 방식
    public String findMenuByCode(@PathVariable int menuCode, Model model) {
//       logger.info("menuCode: {}", menuCode);       // 두번째에 있는 매개변수가 왼쪽 중괄호에 들어감
        // 결과: 2024-02-28T09:41:26.336+09:00  INFO 32708 --- [nio-8888-exec-1] c.o.s.menu.controller.MenuController     : menuCode: 7
        // 이렇게 뜸

        // lombok으로 log 찍기
        log.info("menuCode: {}", menuCode);



        return null;
    }
}
