package com.ohgiraffers.userservice.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data           // 임의상 써주기
@Entity
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // auto-increment
    private Long id;

    @Column(nullable = false, length = 80, unique = true)       // unique 제약조건까지 걸기
    private String email;

    @Column(nullable = false, length = 21)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    // 실행시키면 table 만들어짐 - 이름 딱히 안 붙였기 때문에 그대로 생성됨
    // 껐다 킬 때마다 새로 테이블을 만듦
    // Hibernate:
    //    drop table if exists tbl_user
    // Hibernate:
    //     create table tbl_user (
    //         id bigint not null auto_increment,
    //         name varchar(21) not null,
    //         email varchar(80) not null,
    //         encrypted_pwd varchar(255) not null,
    //         user_id varchar(255) not null,
    //         primary key (id)
    //     ) engine=InnoDB

}
