package com.ohgiraffers.section02.crud;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트() {
        // 다중행 조회는 안 됨

        // given
        int menuCode = 2;

        // when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트() {

        // given
        Menu menu = new Menu();
        menu.setMenuName("꿀발린추어탕");
        menu.setMenuPrice(7000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {

            entityManager.persist(menu);
            // insert가 아니라 따로 관리해달라는 뜻

            entityTransaction.commit();
            // 제대로 돌아갔으면 커밋시키기

        } catch (Exception e) {
            entityTransaction.rollback();
        }

        // then
        Assertions.assertTrue(entityManager.contains(menu));
        // menu 객체가 현재 영속 상태로 관리되는지 확인

    }

    // 위 테스트 할 때의 연속성 컨텍스트랑 엔티티는 다름! 왜냐하면 AfterEach를 통해 닫아버렸기 때문에 새로 생성됨
    @Test
    public void 메뉴_이름_수정_테스트() {

        // given
        Menu menu = entityManager.find(Menu.class, 2);
        System.out.println("menu = " + menu);

        String menuNameToChange = "갈치스무디";

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            menu.setMenuName(menuNameToChange);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }

        // then
        Assertions.assertEquals(menuNameToChange, entityManager.find(Menu.class, 2).getMenuName());
    }


    @Test
    public void 메뉴_삭제하기_테스트() {

        // given
        Menu menuToRemove = entityManager.find(Menu.class, 1);

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.remove(menuToRemove);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }

        // then
        Menu removeMenu = entityManager.find(Menu.class, 1);
        Assertions.assertEquals(null, removeMenu);

    }
}
