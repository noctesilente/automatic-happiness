package com.ohgiraffers.orderservice.controller;

import com.ohgiraffers.orderservice.dto.OrderDTO;
import com.ohgiraffers.orderservice.service.OrderService;
import com.ohgiraffers.orderservice.vo.ResponseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/users/{userId}")
    public ResponseEntity<List<ResponseOrder>> getUserOrders(@PathVariable("userId") String userId) {
        List<OrderDTO> orderDTOList = orderService.getUserOrders(userId);
        // 로그 찍기
//        orderDTOList.stream().forEach(System.out::println);

        // model mapper 안 쓰고 직접 하기
        List<ResponseOrder> returnValue = orderDTOToResponseOrder(orderDTOList);

//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    private List<ResponseOrder> orderDTOToResponseOrder(List<OrderDTO> orderDTOList) {
        List<ResponseOrder> responseList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            ResponseOrder responseOrder = new ResponseOrder();
            responseOrder.setOrderDate(orderDTO.getOrderDate());
            responseOrder.setOrderTime(orderDTO.getOrderTime());
            responseOrder.setOrderMenus(orderDTO.getOrderMenus());

            responseList.add(responseOrder);
        }

        return responseList;
    }


    // 마이크로 서비스 간의 통신을 할 것
    // 3가지 방법이 있는데 1. RestTemplate 2. FeignClient 3. Kafka
    // 우리는 2번을 쓸 것
    // 1. url 직접 지정 2. gateway랑 eureka 서버 연동 이유 - 각각을 이름으로 다루기 위함
    // 3. 결과적 정합성만 맞으면 됨
    // 서버 포트 0으로 바꿔주고 user service build.gradle로 가기
    // dependency에 openfeign 추가

}
