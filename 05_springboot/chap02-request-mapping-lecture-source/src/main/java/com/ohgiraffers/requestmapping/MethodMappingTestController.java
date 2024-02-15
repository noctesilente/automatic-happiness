package com.ohgiraffers.requestmapping;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {
    // pojo인데 여기에 @Controller를 달 것

    /* 설명. 핸들러(=Controller) 메소드(어노테이션을 활용해서 요청 방식 및 경로에 따라 각각 메소드가 작성됨) */
    @RequestMapping(value = "/menu/regist", method = RequestMethod.GET)
//    @RequestMapping(value = "/menu/regist")     // GET 요청뿐 아니라 다른 요청 방식도 처리됨
    // 뒤에 method = RequestMethod.GET 안 써주면 get이나 post나 다 받음! = Get이나 Post나 둘 다 처리했다는 뜻
    // 방식마다 다르게 처리하고 싶으면 달기
    public String registMenu(Model model) {
        // Model은 응답할 페이지의 재료를 담는 객체이다.
        // request랑 같다고 생각 - request도 응답 전까지 값을 담아둘 수 있음
        System.out.println("/menu/regist 경로의 GET 요청 받아보기");

        // key와 value 형태로 model에 담기
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함");

        /* 설명.  */
        return "mappingResult";
        // 사용자한테 주고 싶은 화면을 만든 후 우리가 만든 파일명을 내보내는 것
        // 동적으로 건드려서 만든 것이라서 templates에 mappingResult.html 만들기
    }
    // 예전에는 요청 한 건당 서블릿을 하나 만들었어야 하는데 이제는 메소드를 하나 만들면 됨
    // 결국 서블릿 하나가 메소드 하나로 바뀐 셈이라고 보면 됨

    @RequestMapping("/menu/modify")
    public String modifyMenu(Model model) {
        model.addAttribute("message", "POST 방식의 메뉴 수정용 핸들러 메소드 호출함");

        return "mappingResult";
    }

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model) {
        model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러 메소드 호출함");

        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model) {
        model.addAttribute("message", "POST 방식의 메뉴 삭제용 핸들러 메소드 호출함");

        return "mappingResult";
    }
}
