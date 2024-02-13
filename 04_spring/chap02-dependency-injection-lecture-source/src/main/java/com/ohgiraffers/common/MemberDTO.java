package com.ohgiraffers.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {
    private int sequence;                   // 회원번호
    private String name;                    // 이름
    private String phone;                   // 휴대폰 번호
    private String email;                   // 이메일
    private Account personalAccount;        // 개인계좌
//    private Account personalAccount = new Account();
    // 이제 이런 식이 사라짐!
    // Account는 클래스가 아니라 인터페이스로 만들 것
}
