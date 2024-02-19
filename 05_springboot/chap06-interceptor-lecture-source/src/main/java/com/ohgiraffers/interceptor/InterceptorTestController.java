package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 3-1 */
@Controller
public class InterceptorTestController {
    /* 3-2 */
    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException{
        System.out.println("핸들러 메소드 호출함...");

        Thread.sleep(1000);

        return "result";
    }
}
