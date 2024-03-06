package com.ohgiraffers.userservice.client;

import com.ohgiraffers.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* 설명.
 *  FeignClient는 마이크로 서비스의 이름(유레카 서버에 뜨는 이름이자 spring.application.name에 해당하는 이름)
 *  으로 통신하는 방식이다.
 *  어노테이션의 url은 gateway의 주소를 쓰고, name은 마이크로 서비스의 이름을 쓰게 된다.
* */

@FeignClient(name="swcamp-order-service", url="localhost:8000")
public interface OrderServiceClient {
    // gateway를 거쳐서 가는 것 - user service로 바로 가는 것이 아니라

    /* 설명. gateway가 알고 있는 마이크로 서비스의 접두사(라우팅 시 설정한 요청 경로)를 추가해서 요청 경로를 작성한다. */
    @GetMapping("/order-service/orders/users/{userId}")
    // gateway 라우팅에 대한 접두사인 order-service가 필요
    List<ResponseOrder> getUserOrders(@PathVariable("userId") String userId);
    // List<ResponseOrder> = 오더 서비스가 반환한 바디에 담겼던 것
    // responseorder도 vo에 만들어줘야 함
    // order service에서 /orders/users/{userId}에 썼던 거 가져오면 됨


}
