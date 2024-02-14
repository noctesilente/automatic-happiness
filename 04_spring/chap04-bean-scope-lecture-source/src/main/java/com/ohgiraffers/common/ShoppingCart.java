package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // final을 붙이는 이유: list 타입을 필드에 선언할 때는 습관적으로라도 = new ArrayList를 붙여야 된다고 생각하기
    // 아니면 nullpointerexception이 생김
    // 초기화를 안 하면 에러가 뜨기 때문에 
    // collection에는 final을 붙이는 것이 좋음
    // shoppingcart 에 또다른 객체를 덮어씌우면 카트가 바뀌면 안 되는 거니까 final을 쓰는 것
    // 이 리스트만 쓸 것이기 때문에!
    // final을 붙이기 위해서 쓰는 것이 생성자 주입
    private final List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /* 설명. 카트에 물품 담는 기능 */
    public void addItem(Product item) {
        items.add(item);
    }

    /* 설명. 카트의 물품 꺼내기 */
    public List<Product> getItems() {
        return items;
    }
}
