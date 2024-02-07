package com.ohgiraffers.section01.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {

    public ExceptionHandlerServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명.
         *  request는 사실상 하나의 요청부터 응답까지 유지되는 임시 저장 공간이기도 하다.
         *  이 공간에 값을 담기 위해서는 setAttribute()라는 메소드를 써서 담을 수 있고
         *  값을 다시 추출하고자 할 때는 getAttribute()라는 메소드를 쓸 수 있다.(key와 value 형태로)
         * */

        /* 설명. request attribute에는 에러와 관련된 정보가 들어있다. 확인해 보자. */
        // request는 헤더와 바디도 있지만 웹 쪽에서 요청이 들어와서 응답할 때까지 써먹을 수 있는 임시 저장 공간이라고 생각하기
        // 내장 객체라고 해서 총 4가지가 있음 - 주로 쓰는 건 session하고 request
        // request에 담겨 있는 것 중에 attribute라는 개념 꺼내기
        // 에러가 생겨서 여기로 오면 request의 attribute에 뭐가 들어있는지 확인하는 것
        Enumeration<String> attrNames = req.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            System.out.println(attrNames.nextElement());
        }

        // 결과:
        // jakarta.servlet.forward.request_uri
        // jakarta.servlet.forward.context_path
        // jakarta.servlet.forward.servlet_path
        // jakarta.servlet.forward.mapping
        // jakarta.servlet.error.message
        // jakarta.servlet.error.servlet_name
        // jakarta.servlet.error.request_uri
        // jakarta.servlet.error.status_code


        /* 설명. request의 attribute에 에러 관련된 것들을 활용하여 에러 전용 페이지를 만들어 웅답 */
//        int statusCode = req.getAttribute("jakarta.servlet.error.status_code");
        // 에러 발생 -> Attribute는 꺼내면 무조건 반환형이 Object이라서 다운캐스팅 필요
        int statusCode = (int)req.getAttribute("jakarta.servlet.error.status_code");
        String message = (String)req.getAttribute("jakarta.servlet.error.message");
        String servletName = (String) req.getAttribute("jakarta.servlet.error.servlet_name");

        System.out.println("statusCode = " + statusCode);
        System.out.println("message = " + message);
        System.out.println("servletName = " + servletName);

        // 결과:
        // statusCode = 404
        // message = 페이지를 못 찾겠습니다!
        // servletName = com.ohgiraffers.section01.exception.Show404ErrorServlet

        StringBuilder errorPage = new StringBuilder();
        errorPage.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1 align=\"center\">")
                .append(statusCode)
                .append("-")
                .append(message)
                .append("<br>\n")
                .append("<p>에러 발생한 서블릿 명: ")
                .append(servletName)
                .append("</p>")
                .append("</h1>\n")
                .append("</body>\n")
                .append("</html>");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print(errorPage);
        out.flush();
        out.close();

    }
}
