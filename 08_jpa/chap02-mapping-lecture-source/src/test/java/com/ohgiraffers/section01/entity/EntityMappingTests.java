package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityMappingTests {

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
    public void 테이블_만들기_테스트() {

        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(member);
//        entityManager.flush(); -> 안 됨!

//        entityTransaction.commit();

        // then
        Member foundMember = entityManager.find(Member.class, 1);
//        assertEquals(member, foundMember);
        foundMember.setNickname("동해번쩍");

        entityTransaction.commit();
        assertEquals(member, foundMember  );
    }


    // 결과:
    // Hibernate:
    //    create table tbl_member_section01 (
    //        member_no integer not null,
    //        enroll_date datetime(6),
    //        address varchar(255),
    //        email varchar(255),
    //        member_id varchar(255),
    //        member_pwd varchar(255),
    //        member_role varchar(255),
    //        nickname varchar(255),
    //        phone varchar(255),
    //        status varchar(255),
    //        primary key (member_no)
    //    ) engine=InnoDB

    // Hibernate:
    //    insert
    //    into
    //        tbl_member_section01
    //        (address, email, enroll_date, member_id, member_pwd, member_role, nickname, phone, status, member_no)
    //    values
    //        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    // Hibernate:
    //    update
    //        tbl_member_section01
    //    set
    //        address=?,
    //        email=?,
    //        enroll_date=?,
    //        member_id=?,
    //        member_pwd=?,
    //        member_role=?,
    //        nickname=?,
    //        phone=?,
    //        status=?
    //    where
    //        member_no=?
}
