package com.ohgiraffers.userservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    // 순서는 상관 없는데 엔티티랑 이름이 동일해야 함 -> 그래야지 modelmapper가 옮겨줌
    private String name;
    private String email;
    private String pwd;

    private java.util.Date enrollDate;
    private String userId;

}
