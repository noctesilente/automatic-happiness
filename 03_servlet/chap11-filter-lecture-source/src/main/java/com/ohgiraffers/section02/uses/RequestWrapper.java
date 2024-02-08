package com.ohgiraffers.section02.uses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RequestWrapper extends HttpServletRequestWrapper {
    // httpServletRequest를 직접 상속받는 걸 막아두었기 때문에 wrapper로
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    // 왜 getParameter 재정의?
    @Override
    public String getParameter(String key) {

        /* 설명. 'password'라는 키 값으로 getParameter 사용 시에 그 반환값은 암호화해서 반환 */
        String value = "";
        if ("password".equals(key)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            value = passwordEncoder.encode(super.getParameter("password"));
        } else {                    // 그 외의 키 값 사용 시에는 기존대로 동작
            value = super.getParameter(key);
        }

        return value;

        // 이제 여기서 열심히 만든 필터로 갈아끼기 -> PasswordEncryptFilter로 돌아감
    }
}
