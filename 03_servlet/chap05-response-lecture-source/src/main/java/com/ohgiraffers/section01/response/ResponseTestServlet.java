package com.ohgiraffers.section01.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseTestServlet extends HttpServlet {
    // 지금은 요청마다 servlet 클래스를 하나씩 만들어야 함

    public ResponseTestServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명.
         *  1. 요청 받기(http method GET/POST 요청에 따른 parameter로 전달 받은 데이터를 꺼낼 수 있다.)
         *  2. 비즈니스 로직 처리(DB 접속과 CRUD에 대한 로직 처리 시작 부분 -> 서비스 계층 호출(MVC2  구조 기반))
         *  3. 응답하기(문자열을 통한 동적인 웹(HTML 페이지) 페이지를 만들어서 스트림을 통해 내보내기)(SSR 기술)
        * */

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>servlet response</h1>\n")
//                .append("<h1>한글 servlet response</h1>\n")
//                이런 식으로 한글 들어가면 깨져서 나옴
                .append("</body>\n")
                .append("</html>");
        // 이렇게 쭉 써주기 귀찮으니 jsp 사용하면 안 그래도 됨= 쉽게 만들어줌

        // 원래는
        resp.setContentType("text/plain");
        // 문자열이라고 인식해도 웹 브라우저가 그냥 띄웠음

        // 톰캣 10버전이 되면서 html 형태가 디폴트로 잡힘
        // 대신 한글에 대한 지원이 안 됨
        // 2가지 방법 -

        /* 설명. tomcat 10버전 이전 */
        /* 필기. 1. MIME 타입과 인코딩 방식을 response 객체를 이용해 따로 설정 */
        // 1. 이렇게 두 줄
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
        // 텍스트가 날아가니 인코딩 해주라는 뜻
        // 이렇게 하면 한글이 안 깨짐 - 하지만 좀 애매함...

        /* 필기. 2. MIME 타입과 인코딩 방식을 setContentType으로 한 번에 설정 */
        // 2. 아님 이렇게 한 줄
        resp.setContentType("text/html; charset=UTF-8");

        /* 설명. tomcat 10버전 이후 */
        /* 필기. MIME 타입만 명시해도 인코딩 방식도 UTF-8로 적용됨 */
        // 최종. 지금은 이렇게 써주기
//        resp.setContentType("text/html");



        PrintWriter out = resp.getWriter();
        // getWriter = response가 알고 있는 경로로 스트림을 열게 됨
        out.print(responseBuilder);
        // fileoutputstream이랑 똑같음 = 한 줄 출력이 가능
        out.flush();
        // outputstream은 항상 flush 해야 함
        out.close();
    }
}
