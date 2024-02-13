package com.ohgiraffers.common;

import lombok.*;

// annotation 붙이면 굳이 밑에 다 안 써도 됨
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {
    private int sequence;
    private String id;
    private String pwd;
    private String name;
}
