package com.ohgiraffers.userservice.vo;

import lombok.Data;

/* 설명. 회원가입을 위해 넘어온 사용자 정보를 받아줄 Command 객체용 VO 생성 */
@Data
// 원래는 쓰지 말아야 되지만... 지금은 편의상 붙임
// 사용자가 넘겨준 값
public class RequestUser {
    private String name;
    private String email;
    private String pwd;
}
