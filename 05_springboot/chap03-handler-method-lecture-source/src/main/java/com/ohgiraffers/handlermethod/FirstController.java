package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller

/* 설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/xxx의 요청을 받게 될 때 클래스에 어노테이션을 추가할 수 있다. */
@RequestMapping("/first")
// 여기 있는 메소드는 전부 /first로부터 넘어온다는 뜻
// 이거 꼭 주의! 밑에 있는 메소드들 수정할 때 까먹고 오류가 생길 수도 있음!

/* 설명. 이 Controller 클래스의 핸들러 메소드에서 Model에 'id'라는 키 값으로 담는 값들은 Session에 담으라는 어노테이션 */
@SessionAttributes("id")
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


    /* 2. @RequestParam을 이용해서 요청 파라미터 전달 받기 */
    @GetMapping("modify")
    public void modify() {

    }

    /* 설명.
     *  request의 parameter로 넘어오는 값들의 key값과 일치하는 변수명을 작성하고 @RequestParam
     *  어노테이션을 적용하면 알아서 값을 꺼내고 해당 매개변수의 자료형에 맞게 변환까지 해 준다.
     *  @RequestParam 어노테이션은 생략도 가능하다.
     *  (스프링부터는 컨트롤러의 파싱(Parsing) 작업이 간소화 된다는 것을 알 수 있다.)
     * */

    /* 설명.
     *  @RequestParam의 속성들
     *  1. defaultValue: 사용자의 입력 값이 없거나("") 아니면 request의 parameter 키 값과 일치하지 않는
     *                   매개변수 명 사용 시 매개변수가 가질 기본 default 값을 작성한다./
     *  2. name: request parameter의 키 값과 다른 매개변수 명을 사용하고 싶을 때 request parameter의
     *           키 값을 작성한다.
     * */
    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam(defaultValue = "디폴트", name = "name") String modifyName, @RequestParam(defaultValue = "0") int modifyPrice) {
        // 여기서 @RequestParam도 생략이 가능함! 생략해도 그대로 됨
        // modifyName 으로 바꿔주고 하면 뭘 입력해도 디폴트로 뜸 = 키값이랑 매개변수 이름이 안 맞아서 그럼
        // 그래서 name="name"을 붙여줘야 함
        // defaultValue 붙여주면 사용자가 값을 입력 안 해도 처음부터 0으로 세팅 가능!
        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 가격을 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    // Map으로 받기
    @PostMapping("modify2")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameter) {

        // 대신 string으로 나오기 때문에 자료형 변환은 우리가 해줘야 함
        String modifyName = parameter.get("name2");
        int modifyPrice = Integer.valueOf(parameter.get("modifyPrice2"));

        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 가격을 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }


    /* 3. @ModelAttribute를 이용하여 파라미터 전달 받기 */
    @GetMapping("search")
    public void searchMenu() {
    }

    /* 설명.
     *  핸들러 메소드에 우리가 작성한 클래스를 매개변수로 작성하면 스프링이 객체를 만들어 주고 setter로
     *  값도 주입해 준다.(커맨드 객체)
     * */
    /* 설명.
        @ModelAttribute 어노테이션을 활용하면 커맨드 객체를 모델에도 담아주며 어트리뷰트의 키 값을 지정할 수 있다.
     *  (키 값이 없으면 타입의 낙타봉 표기법이 키 값이다.)
    * */
    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu) {
        // @ModelAttribute = 모델에 담아줌 커맨드 객체의 모델화 - 모델에 담아주면 다음 페이지에도 패스 가능
        // "menu" 안 적으면 키값의 낙타봉 표기법으로 감 - menuDTO로 감 그러면 에러가 발생...

        // MenuDTO로 한 번에 다 받을 수 있음
        // 꼭 setter 안 쓰더라도 매개변수에 적으면 스프링이 알아서 잘해줌
        // 이런 걸 커맨드 객체라고 함
        System.out.println("menu = " + menu);
        // 결과: menu = MenuDTO{name='df', price=12, categoryCode=1, orderableStatus='Y'}
        // 이런 식으로 로그가 찍힘

        return "first/searchResult";
    }


    /* 3-1, 3-2. HttpSession 이용하기 */
    /* HttpSession을 매개변수로 선언하기 */
    @GetMapping("login")
    public void login() {
    }

    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam String id) {
        session.setAttribute("id", id);
        return "first/loginResult";
        // id랑 pwd 두 개를 받았지만 우선 id만 하는 걸로
        // getsession 할 필요 없이 사용자의 아이디에 따라 사물함 제공
        // 사용자 전용 사물함에 담겨 있고, 입력한 값이 키와 밸류 형태로 들어감
        // 한 번 로그인하면 그 값이 30분 동안 남아있음 = 세션
        // 모델에서 재료를 꺼내는 것이 아니라 세션에서 재료를 꺼낼 수 있음 - 서버가 갖고 있는 내장 객체를 꺼내서 동적 페이지를 만들 수 있음
        // loginResult 만들고 페이지에서 꺼낼 수 있음

        // session은 서버이기 때문에 가능함
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {
        session.invalidate();

        return "first/loginResult";
    }


    /* @SessionAttribute 사용하기 */
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {
        // 웬만한 건 다 모델로 담고 특정 키 값으로 담기는 것들은 session으로 담기
        // 위에
//        이 Controller 클래스의 핸들러 메소드에서 Model에 'id'라는 키 값으로 담는 값들은 Session에 담으라는 어노테이션 */
//        @SessionAttributes("id")
        // 추가해줌
        model.addAttribute("id", id);

        return "first/loginResult";
        // 이걸로 담으면 invalidate가 안 돼서 로그아웃이 안 됨! loginResult 들어가서 로그아웃 처리할 핸들러 메소드 추가
    }

    /* 설명. @SessionAttributes 방식으로 Session에 담긴 값은 SessionStatus에서 제공하는 setComplete()로 만료시켜야 한다. */
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "first/loginResult";
    }


    /* 4. RequestBody 등의 핸들러 메소드의 어노테이션들을 활용한 전달 받기 */
    @GetMapping("body")
    public void body() {
    }

    @PostMapping("body")
    public void body(@RequestBody String body, @RequestHeader("content-type") String contentType,
                     @CookieValue(value = "JSESSIONID") String sessionId) {
        System.out.println("body = " + body);
        System.out.println("contentType = " + contentType);
        System.out.println("sessionId = " + sessionId);

        // 결과:
        // body = name=reger&price=123&categoryCode=1&orderableStatus=Y
        // contentType = application/x-www-form-urlencoded
        // sessionId = 7F1C80F561B2A4A4834BF79388289CA4
    }
}
