package com.ohgiraffers.userservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    // requestuser 랑 다르게 나중에 하나가 추가될 것

    private String name;        // 사용자 이름
    private String email;       // 사용자 이메일
    private String userId;      // 사용자 id(회원번호 아님)
    // 화면에 뿌릴 것 감안해서 pwd가 아닌 id로 한 것
    // 화면에 뿌릴 값

    /* 설명. FeignClient 이후(다른 도메인 서버와 통신해서 값을 가져온 이후) */
    private List<ResponseOrder> orders;
}
