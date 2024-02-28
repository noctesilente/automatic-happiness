package com.ohgiraffers.springdatajpa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String main() {
        return "main/main";
        // templates 안에서 main 폴더 안에 main 파일을 찾아감
    }
}
