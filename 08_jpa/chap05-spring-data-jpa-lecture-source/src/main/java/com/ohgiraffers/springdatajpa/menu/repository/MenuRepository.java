package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    // 어떤 엔티티와 관련된 걸 할 건지 + Menu entity의 PK값 써주기
    List<Menu> findByMenuPriceGreaterThan(int menuPrice);
}
