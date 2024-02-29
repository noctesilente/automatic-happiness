package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter       // 원래는 엔티티에 세터 안 쓰는데... 오류 떠서 어쩔 수 없이 추가
@ToString
//@Data       // 다 만들어주지만 내부적으로 다 만들어주기 때문에 매우 비효율적 -> 이거 쓰는 거 지양하기
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;


}
