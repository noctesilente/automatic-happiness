package com.ohgiraffers.section01.aop;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {
    private Long id;
    // 회원 수가 많을 때를 대비해서 long형으로 만듦
    private String name;
}
