package com.ohgiraffers.eurekaclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // view resolver를 거치지 않고 자바의 모든 값이 json 문자열을 거치는 것
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    // 소스 코드를 건드리는 거니 새로 추가할 때마다 재기동을 해야 함
}
