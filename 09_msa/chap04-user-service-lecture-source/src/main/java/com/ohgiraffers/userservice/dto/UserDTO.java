package com.ohgiraffers.userservice.dto;

import com.ohgiraffers.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    // 순서는 상관 없는데 엔티티랑 이름이 동일해야 함 -> 그래야지 modelmapper가 옮겨줌
    private String name;
    private String email;
    private String pwd;

    private java.util.Date enrollDate;
    private String userId;

    /* 설명. FeignClient 이후 */
    // 주문 내역도 같이 실어서 보내주겠다는 것
    private List<ResponseOrder> orders;

}
