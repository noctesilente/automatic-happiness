package com.ohgiraffers.section03.persistencecontext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class A_EntityLifeCycleTests {

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

    /* 필기.
     *  영속성 컨텍스트는 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
     *  엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다.
     *
     * 필기.
     *  엔티티의 생명 주기
     *  비영속, 영속, 준영속, 삭제 상태
    * */

    @Test
    public void 비영속성_테스트() {

        // 영속 상태 객체 만들기 -> 관리되는 메뉴 객체
        Menu foundMenu = entityManager.find(Menu.class, 11);
//        System.out.println("foundMenu = " + foundMenu);
        // 결과: foundMenu = Menu{menuCode=11, menuName='정어리빙수', menuPrice=10000, categoryCode=10, orderableStatus='Y'}

        /* 설명. 영속 상태의 객체에서 값을 추출해 담더라도 새로 생성되어 영속성 컨텍스트와 관련 없는 객체는 비영속 상태이다. */
        // 꺼내서 객체 만들기 -> 방금 만든 비영속성 객체 - 매니저가 관리하지 X
        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());

        boolean isTrue = (foundMenu == newMenu);

        assertFalse(isTrue);
    }

    @Test
    public void 영속성_연속_조회_테스트() {
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 11);

        boolean isTrue = (foundMenu1 == foundMenu2);

        assertTrue(isTrue);
    }

    @Test
    public void 영속성_객체_추가_테스트() {

        /* 설명.
         *  이 예제에서는 menuCode 필드 값에 직접 값을 주고자 한다.(auto_increment 개념 안 쓸 예정)
         *  @GeneratedValue 부분을 주석 하고 테스트를 작성하자.
        * */
        // PK라는 속성이 매우 중요 항상 PK로 대화함
        Menu menuToRegist = new Menu();
        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setMenuPrice(10000);
        menuToRegist.setCategoryCode(1);
        menuToRegist.setOrderableStatus("Y");

        entityManager.persist(menuToRegist);
        // 비영속 상태의 객체를 만든 다음에 매니저한테 관리하라고 주기
        // 영속 상태로 바꿔주는 것 = persist

        // 다시 불러오기
        Menu foundMenu = entityManager.find(Menu.class, 500);
        // 처음에 있던 객체 = 관리자한테서 불러온 객체
        boolean isTrue = (menuToRegist == foundMenu);

        assertTrue(isTrue);
    }
    // 다중행일 때만 JPQL로 조회

    @Test
    public void 영속성_객체_추가_값_변경_테스트() {
        Menu menuToRegist = new Menu();
        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setMenuPrice(10000);
        menuToRegist.setCategoryCode(1);
        menuToRegist.setOrderableStatus("Y");

        entityManager.persist(menuToRegist);
        // 바로 수정
        menuToRegist.setMenuName("메론죽");

        Menu foundMenu = entityManager.find(Menu.class, 500);

        assertEquals("메론죽", foundMenu.getMenuName());
        // 맡긴 객체의 setter로 수정을 하면 맡겨진 객체도 바뀌는지  -> 바뀜
        // 동일성이 유지되는지 -> 됨
    }

    @Test
    public void 준영속성_detach_테스트() {

        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);
        // 아무런 값도 없는 상태에서는 select문이 만들어져 가게 됨

        // 준영속 = 엔티티 매니저가 관리는 하지 않는 상태
        /* 설명.
         *  영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태가 되게 한 것을 준영속 상태라고 한다.
         *  detach가 준영속 상태를 만들기 위한 메소드이다.
        * */
        entityManager.detach(foundMenu2);

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        assertEquals(5000, entityManager.find(Menu.class, 11).getMenuPrice());
//        assertEquals(5000, entityManager.find(Menu.class, 12).getMenuPrice());
        // 이 줄에서 에러 발생 -> 12번 조회, 12번이 없기 때문에 다시 가져옴 =/= 준영속 상태인 것 -> actual 값은 db에 있는 값이 나오기 때문에 오류 발생

    }

    @Test
    public void 준영속성_clear_close_테스트() {
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);

        /* 설명. 영속성 컨텍스트로 관리되던 엔티티 객체들을 모두 비영속 상태로 바꿈 */
//        entityManager.clear();

        /* 설명. 영속성 컨텍스트 및 엔티티 매니저까지 종료해 버린다.(사용 불가) */
        entityManager.close();

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        /* 설명. DB에서 새로 조회해 온 객체를 영속 상태로 두기 때문에 전혀 다른 결과가 나온다. */
        assertEquals(5000, entityManager.find(Menu.class, 11).getMenuPrice());
        assertEquals(5000, entityManager.find(Menu.class, 12).getMenuPrice());

    }

    // 병합 = update 또는 insert가 일어남
    @Test
    public void 병합_merge_수정_테스트() {
        Menu menuToDetach = entityManager.find(Menu.class, 2);
        entityManager.detach(menuToDetach);

        menuToDetach.setMenuName("수박죽");
        Menu refoundMenu = entityManager.find(Menu.class, 2);
        // refoundMenu에는 기존의 메뉴 이름이 있다.

        System.out.println(menuToDetach.hashCode());
        System.out.println(refoundMenu.hashCode());

        // 다시 가져오는 것
        entityManager.merge(menuToDetach);

        Menu mergedMenu = entityManager.find(Menu.class, 2);
        assertEquals("수박죽", mergedMenu.getMenuName());
    }

    @Test
    public void 병합_merge_삽입_테스트() {
        Menu menuToDetach = entityManager.find(Menu.class, 2);
        entityManager.detach(menuToDetach);

        // 잠깐 떼어놓은 2번을 999번 수박죽으로 바꾼다는 뜻
        menuToDetach.setMenuCode(999);
        menuToDetach.setMenuName("수박죽");

        entityManager.merge(menuToDetach);

        // pk 999인 수박죽이라는 이름을 가진 메뉴 타입의 엔티티가 들어옴
        // 잠깐 떼서 수정한 다음 다시 합친 것 -> 그럼 전혀 다른 새 것이 됨
        // 2번 다시 조회하면 2번이 다시 쌓임 - db에서

        Menu newMenu = entityManager.find(Menu.class, 2);
        Menu mergedMenu = entityManager.find(Menu.class, 999);
        assertNotEquals(mergedMenu.getMenuCode(), newMenu.getMenuCode());

    }

    // 엔티티 매니저가 관리하고 있는데 커밋할 때 db 안에 없으면 insert가 되는 것
}
