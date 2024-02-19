package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* 5-1 */
/* 설명.
 *  인터셉터를 사용하는 경우(목적)
 *  : 로그인 체크, 권한 체크, 프로그램 실행 시간 계산 작업 로그 처리, 업로드 파일 처리, 로케일(지역) 설정 등
 *  우리가 해볼 건 프로그램 실행 시간 계산 작업 로그 처리
 *  핸들러 메소드 직전과 직후에 실행되기 때문에 가능
 * */
@Configuration
public class StopwatchInterceptor implements HandlerInterceptor {

    private final MenuService MENUSERVICE;
    /* 설명. 필터와 달리 인터셉터는 빈을 활용할 수 있다. (생성자 주입 활용) */
    /* 8-1 */
    // 주입 받을 때는 final를 하는 게 좋음

    public StopwatchInterceptor(MenuService MENUSERVICE) {
        /* 8-2 */
        this.MENUSERVICE = MENUSERVICE;
    }

    /* 5-2 */
    // CTRL + O 누르고 셋 다 오버라이딩
    /* 설명. boolean형에 따라 핸들러 메소드가 실행될 수도 있고 안 될 수도 있도록 할 수 있으며 핸들러 메소드 이전 전처리 목적이다. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 8-3. 핸들러 메소드 가기도 전인데 서비스에 있는 메소드 호출 */
        // 이 한 줄 추가하기
        // 좋은 패턴은 아니지만 경우에 따라서는 컨트롤러를 건너뛰고 메소드를 호출할 수 있음
        // 굉장히 특별한 경우
//        MENUSERVICE.method();

        System.out.println("preHandle 호출함... (핸들러 메소드 이전)");

        /* 9-1 */
        long startTime = System.currentTimeMillis();
        // 값이 넘어와야 되니까 request에 담은 것
        request.setAttribute("startTime", startTime);

        return true;
        // false를 반환하면 핸들러 메소드가 등장하지 않고 true를 반환해야지 핸들러 메소드가 작동함
    }

    /* 5-3 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 호출함... (핸들러 메소드 이후)");

        /* 9-2 */
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        request.removeAttribute("startTime");

        /* 9-3 */
        // modelandview 반환도 해 줌 -> 우리가 한 모든 것들이 modelandview로 반환됨! - 다 바꿈
        // 결국 핸들러 메소드가 반환한 메소드나 뷰 이름 등등이 다 modelandview로 넘어오는 것
        // 핸들러 메소드가 동작하는 데에 걸리는 시간 - 실행 시간
        // modelandview에 추가를 해놓으면 뷰 리졸버한테 가서 그 재료를 가지고 활용을 할 수 있음
        // 그러면 interval이라는 재료를 어디서 활용할 수 있냐면 result.html에서 이 재료를 추가할 수 있음
        modelAndView.addObject("interval", endTime - startTime);
    }

    // 인터셉터 세팅 후 등록까지 해줘야 함 -> WebConfiguration 클래스 만들기 -> 6번으로 감

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
