package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 1-1 */
@Controller
public class MainController {

    /* 1-2 */
    @RequestMapping("/")
    public String defaultlocation() {
        return "main";
    }

    /* 1-3 */
    @RequestMapping("/main")
    public void main() {}
}
