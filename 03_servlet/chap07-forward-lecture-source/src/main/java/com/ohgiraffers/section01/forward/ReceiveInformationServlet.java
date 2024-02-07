package com.ohgiraffers.section01.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {

    public ReceiveInformationServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);
        // 창에 입력했을 때 가져옴
        // 결과:
        // userId = user01
        // password = pass01

        // id와 password를 받으면 매치되는 사람이 있는지 없는지를 찾아야 함
        // 원래는 db와 연결해야 되는데 여기서는 안 하기
        /* 설명.
         *  이 부분에서 id와 pwd에 해당하는 user의 정보를 select하고 확인하는 비즈니스 로직(BL)이
         *  수행되어야 한다.(이름을 조회해서 이름을 알게 됨)
         *  우리는 제대로 조회가 되었다는 가정 하에 'XXX님 환영합니다.'와 같은 메시지를 출력하는 화면을
         *  만들어 응답해 보자.
        * */
        // db에서 조회를 했다는 건 실제 회원이 있다는 걸 알고 이름을 가져왔다는 것
        // 추가적인 정보를 갖고 왔고 서블릿으로 넘겨주려면 이 정보까지 넘겨주어야 된다는 뜻
        // 추가적으로 가져온 건 req에 없음 -> 그래서 req의 attribute에 담아주는 작업을 함

        req.setAttribute("userName", "홍길동");
        // req에 있는 정보를 그대로 다음 서블릿으로 넘겨줌
        // Attribute랑 Parameter는 다르니까 헷갈리지 말기


        /* 설명. 화면을 만들어 응답하는(화면 잘 만드는) 서블릿에게 포워딩(위임) */
        // dispatcher = 위임, 넘겨준다는 뜻 - 어떤 서블릿한테 갈지를 정함
        // '/print' 요청을 받을 수 있는 서블릿 정보를 가지고 만들어짐 -> 어디로 갈지 목적지가 생기는 것
        RequestDispatcher rd = req.getRequestDispatcher("print");
        rd.forward(req, resp);


    }
}
