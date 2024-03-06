package com.ohgiraffers.orderservice.vo;

import com.ohgiraffers.orderservice.dto.OrderMenuDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseOrder {
    // 요청하는 쪽에서 줄 데이터 적기
    // 주문 한 건당 이렇게 둘 것 -> 여러 건 주문에 대해서 받고 싶으면 list로 받음 -> controller

    private String orderDate;
    private String orderTime;

    private List<OrderMenuDTO> orderMenus;

}
