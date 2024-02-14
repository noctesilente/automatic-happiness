package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }


        /* 설명. 붕어빵, 딸기우유, 지리산 암반수 bean 객체를 반환 받아 변수에 담는다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 설명. 첫번째 손님이 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        /* 설명. 첫 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart1에 담긴 물품: " + cart1.getItems());



        /* 설명. 두 번째 손님이 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /* 설명. 두 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart2에 담긴 물품:" + cart2.getItems());


        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));

        // 결과:
        // cart1에 담긴 물품: [Bread{bakedDate=Wed Feb 14 12:44:12 KST 2024} Product(name=${bread.carpBread.name}, price=1000), Beverage{capacity=750} Product(name=초코우유, price=1500)]
        // cart2에 담긴 물품:[Beverage{capacity=2000} Product(name=백두산암반수, price=3000)]
        // false
    }
}
