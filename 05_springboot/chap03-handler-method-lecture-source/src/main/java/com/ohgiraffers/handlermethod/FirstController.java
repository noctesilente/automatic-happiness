package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller

/* 설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/xxx의 요청을 받게 될 때 클래스에 어노테이션을 추가할 수 있다. */
@RequestMapping("/first")
// 여기 있는 메소드는 전부 /first로부터 넘어온다는 뜻
// 이거 꼭 주의! 밑에 있는 메소드들 수정할 때 까먹고 오류가 생길 수도 있음!
public class FirstController {

    /* 설명. 반환형이 void인 핸들러 메소드는 요청 경로 자체가 view의 경로 및 이름을 반환한 것으로 바로 해석이 된다. */
    @GetMapping("regist")               // '/regist' 또는 'regist' 둘 다 허용
    // regist 앞에 / 써도 되고 안 써도 됨
//    public String regist() {
//        return "/first/regist";
//        // regist.html을 templates에 만들어야 함
//    }
    // 요청 주소 자체가 페이지 이름이 되겠다 싶은 것들은 반환형이 보이되 아무런 기능이 없는 메소드로 해도 됨
    // 파일 이름이라고 생각하지 말고 뷰의 이름이라고 생각하기
    public void regist() {
    }
    // 처음에는 오류 뜸 - 경로 자체가 파일의 이름이 되게 하려면 앞에 있는 /first/regist까지 생각해줘야 함
    // 즉 first라는 디렉토리 안에 있어야 함 - templates에 first라는 디렉토리를 만든 후 그 안에 regist.html 넣기


    // 비즈니스 로직 - db를 갔다 오기
    /* 설명. request를 쓰게 된다면 request 개념 사용자의 입력값이 존재, model은 동적 페이지의 재료를 담는 용도로 쓰자. */
    @PostMapping("regist")
//    public String registMenu(HttpServletRequest request) {
    // 너무 서블릿에 국한된 기술이기 때문에 WebRequest를 써줌
    // request는 사용자에게 뭘 받기 위함이었음
    public String registMenu(WebRequest request, Model model) {
        // 메뉴 등록
        String name = request.getParameter("name");
        System.out.println("name = " + name);

        int price = Integer.valueOf(request.getParameter("price"));
        int categoryCode = Integer.valueOf(request.getParameter("categoryCode"));

        String message
                = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 "
                + price + "원으로 등록하였습니다!";

        // key와 value 넘기기
        model.addAttribute("message", message);
        // 이걸 담은 다음의 페이지의 재료로 쓰는 것

        return "first/messagePrinter";
        // templates에 있는 first 디렉토리에 messagePrinter.html 파일 만들어주기
        // 요청한 경로랑 보여줄 페이지가 다르기 때문에 void로 하면 안 되고 명시해야 함
    }
}
