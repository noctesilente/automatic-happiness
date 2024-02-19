package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

/* 7-1 */
// 빈 관리하는 걸 보여주기 위해 작성
// MenuService는 콩이기 때문에 인터셉터에서는 이걸 다룰 수 있게 됨
@Service
public class MenuService {

    /* 7-2 */
    public void method() {
        // 콩이 가진 메소드를 바로 접근해서 실행할 것
        System.out.println("Service 메소드 호출 확인...");
    }
}
