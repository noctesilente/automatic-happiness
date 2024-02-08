package com.ohgiraffers.section02.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/header")
public class ResponseHeaderPrintServlet extends HttpServlet {

    public ResponseHeaderPrintServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. 서버의 현재 시간을 동적인 페이지로 만들어 내보내 보자. */
        /* 설명. response 객체를 생성할 때는 MIME 타입 및 인코딩을 고려할 것 */
        resp.setContentType("text/html");

        // response가 알고 있는 것으로 경로를 만들 것
        PrintWriter out = resp.getWriter();

        long currrentTime = System.currentTimeMillis();

        out.print("<h1>" + currrentTime + "</h1>");
        out.print("<h1>" + new java.util.Date(currrentTime) + "</h1>");

        // 원래 close 내부에 flush가 들어있지만 의도적으로 써주는 것이 좋음
        out.flush();
        out.close();

        /* 설명. response 객체의 header 값에 대한 key 값과 value 값을 한 번에 확인해 보기 */
        // Enumeration이 아닌 Collection 타입 반환 -> iterator로 바꿀 수 있음
        Collection<String> responseHeaders = resp.getHeaderNames();
        Iterator<String> iter = responseHeaders.iterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            System.out.println(headerName + ": " + resp.getHeader(headerName));
        }

        // 결과:
        // Content-Type: text/html;charset=UTF-8
        // Transfer-Encoding: chunked
        // Date: Wed, 07 Feb 2024 07:37:01 GMT
        // Keep-Alive: timeout=20
        // Connection: keep-alive

        // 서버의 시간은 클라이언트 시간과 다를 수 있음
        // 새로고침 할 때마다 서버의 시간이 매번 바뀌니 동적으로 페이지가 계속 바뀜
        // 동적인 페이지를 만드는 개념 -> 이 동적인 페이지를 만들기 위해서 tomcat의 WAS를 쓰는 것
        // 주로 데이터베이스의 값을 활용해서 페이지를 만들게 됨
    }
}
