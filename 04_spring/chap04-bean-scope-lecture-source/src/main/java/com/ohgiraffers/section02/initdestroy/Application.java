package com.ohgiraffers.section02.initdestroy;

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
        // 결과: cart1에 담긴 물품: [Bread{bakedDate=Wed Feb 14 11:16:57 KST 2024} Product(name=붕어빵, price=1000), Beverage{capacity=500} Product(name=딸기우유, price=1500)]



        /* 설명. 두 번째 손님이 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /* 설명. 두 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart2에 담긴 물품:" + cart2.getItems());


        /* 설명. main 메소드 종료 전(application 종료 전)에 의도적으로 container를 소멸(bean들도 소멸)해 보자. */
        // 컨테이너를 강제로 종료시켜야지 closeShop이 보임
        ((AnnotationConfigApplicationContext)context).close();


        // 결과:
        // 사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.
        // beanName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // beanName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // beanName = org.springframework.context.annotation.internalCommonAnnotationProcessor
        // beanName = org.springframework.context.event.internalEventListenerProcessor
        // beanName = org.springframework.context.event.internalEventListenerFactory
        // beanName = contextConfiguration
        // beanName = carpBread
        // beanName = milk
        // beanName = water
        // beanName = cart
        // beanName = owner
        // cart1에 담긴 물품: [Bread{bakedDate=Wed Feb 14 11:45:53 KST 2024} Product(name=붕어빵, price=1000), Beverage{capacity=500} Product(name=딸기우유, price=1500)]
        // cart2에 담긴 물품:[Beverage{capacity=500} Product(name=지리산 암반수, price=3000)]
        // 사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.

    }

}
