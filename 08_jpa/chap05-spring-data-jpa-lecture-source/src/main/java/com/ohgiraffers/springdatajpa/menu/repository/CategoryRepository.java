package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // 어떤 엔티티와 관련된 걸 할 건지 - Category Entity + Category entity의 PK값 써주기

    /* 설명. findAll 메소드를 사용할 수 있지만 jpql 또는 native sql로 작성할 수도 있음을 확인한다. */
//    @Query(value = "SELECT m FROM Category m ORDER BY m.categoryCode ASC")    -> jpql
    @Query(value = "SELECT CATEGORY_CODE, CATEGORY_NAME, REF_CATEGORY_CODE FROM TBL_CATEGORY " +
            "ORDER BY CATEGORY_CODE ASC", nativeQuery = true)
    // 쿼리 메소드로 감당이 안 되면 이렇게 쓸 수 있음
    // jpa로 감당 안 되는 걸 어쩔 수 없으니 native query 를 쓸 수 있음
    // 안 써도 작동은 되지만 이렇게도 쓸 수 있다는 걸 보여준 예시 -> 컨벤션을 못 지키고 쓰는 거라 긴 쿼리문을 쓸 때는 mybatis를 쓰는 게 낫다는 의견도 있음
    // 대신 주석 처리하고 쓰는 게 아니라 findAll -> 부모에서 지원해주는 메소드 쓰고 여기에 굳이 안 써도 됨!
    List<Category> findAllCategory();
}
