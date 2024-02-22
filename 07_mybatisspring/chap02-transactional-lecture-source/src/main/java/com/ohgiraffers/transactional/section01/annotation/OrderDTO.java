package com.ohgiraffers.transactional.section01.annotation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderDTO {
    // DTO = 사용자가 입력한 걸 하나로 묶은 것 - 이거 대신에 map을 넘겨줘도 됨

    private LocalDate orderDate;        // 서버의 현재 날짜
    private LocalTime orderTime;        // 서버의 현재 시간
    private List<OrderMenuDTO> orderMenus;      // 한 번의 주문에 고객이 주문한 메뉴들

    public OrderDTO() {
    }

    public OrderDTO(LocalDate orderDate, LocalTime orderTime, List<OrderMenuDTO> orderMenus) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderMenus = orderMenus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderMenuDTO> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(List<OrderMenuDTO> orderMenus) {
        this.orderMenus = orderMenus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderMenus=" + orderMenus +
                '}';
    }
}
