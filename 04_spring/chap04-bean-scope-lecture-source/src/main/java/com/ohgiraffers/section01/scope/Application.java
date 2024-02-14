package com.ohgiraffers.section01.scope;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /* 설명.
     *  bean scope란?
     *   Spring bean이 생성될 때 해당 인스턴스의 범위를 의미한다. 스프링에서는 다양한 bean scope를 제공한다.
     *   1. singleton: 하나의 인스턴스만을 생성하고, 모든 빈이 해당 인스턴스를 공유하며 사용한다.
     *   2. prototype: 매번 새로운 인스턴스를 생성한다.
     *   3. request: Http 요청을 처리할 때마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다.
     *               웹 애플리케이션 컨텍스트에만 해당된다.
     *   4. session: Http 세션당 하나의 인스턴스를 생성하고, 세션이 종료되면 인스턴스를 폐기한다.
     *               웹 애플리케이션 컨텍스트에만 해당된다.
     *   5. globalSession: 전역 세션당 하나의 인스턴스를 생성하고, 전역 세션이 종료되면 인스턴스를 폐기한다.
     *
     * 포턴 애플리케이션 컨텍스트에만 해당된다.
    * */
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // ContextFiguration에서 만든 빈 4개를 잘 들고 있는지 확인해보기
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }
        // 결과:
        // beanName = carpBoard
        // beanName = milk
        // beanName = water
        // beanName = cart


        /* 설명. 붕어빵, 딸기우유, 지리산 암반수 bean 객체를 반환 받아 변수에 담는다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 설명. 첫번째 손님이 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);
        // 실제로는 arraylist에 계속 add 한 것

        /* 설명. 첫 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart1에 담긴 물품: " + cart1.getItems());
        // 결과: cart1에 담긴 물품: [Bread{bakedDate=Wed Feb 14 11:16:57 KST 2024} Product(name=붕어빵, price=1000), Beverage{capacity=500} Product(name=딸기우유, price=1500)]



        /* 설명. 두 번째 손님이 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /* 설명. 두 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart2에 담긴 물품:" + cart2.getItems());
        // 결과: cart2에 담긴 물품:[Bread{bakedDate=Wed Feb 14 11:19:05 KST 2024} Product(name=붕어빵, price=1000), Beverage{capacity=500} Product(name=딸기우유, price=1500), Beverage{capacity=500} Product(name=지리산 암반수, price=3000)]
        // 첫번째 손님 카트에 추가된 걸 볼 수 있음

        // 카트를 꺼내준 게 똑같다 = 싱글톤 - 반드시 객체가 하나 생성됨

        // 주소가 똑같은지 보기
        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));
        // 결과: true
        // 같은 콩 객체라는 뜻


        // ContextFiguration으로 돌아가서 @Scope("prototype") 추가해주면 다른 카트가 됨!
        // 결과:
        // cart1에 담긴 물품: [Bread{bakedDate=Wed Feb 14 11:24:20 KST 2024} Product(name=붕어빵, price=1000), Beverage{capacity=500} Product(name=딸기우유, price=1500)]
        // cart2에 담긴 물품:[Beverage{capacity=500} Product(name=지리산 암반수, price=3000)]
        // false


    }
}
