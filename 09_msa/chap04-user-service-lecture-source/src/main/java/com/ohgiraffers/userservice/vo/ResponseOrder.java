package com.ohgiraffers.userservice.vo;

import lombok.Data;

import java.util.List;

/* 설명. 각 도메인끼리 통신을 하고 조회된 결과가 있다면 이러한 VO(도메인별 중간 객체)에 담는다. */

@Data
public class ResponseOrder {

    private String orderDate;
    private String orderTime;

    private List<OrderMenuVO> orderMenus;
    // 필드명만 동일하면 됨 -> 받는 쪽에서는 이름 상관X
}
