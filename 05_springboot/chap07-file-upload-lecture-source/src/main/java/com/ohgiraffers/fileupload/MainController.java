package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 1-1 */
@Controller
public class MainController {

    /* 1-2 */
    @GetMapping("/")
    public String defaultLocation() {
        return "main";
    }

    /* 1-3 */
    @GetMapping("/main")
    public void main() {}
}
