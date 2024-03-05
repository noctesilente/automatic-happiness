package com.ohgiraffers.userservice.repository;

import com.ohgiraffers.userservice.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 엔티티와 PK 값이 들어감
    // USER라고 하면 헷갈리기 때문에 UserEntity라는 이름으로 만들기

    UserEntity findByEmail(String email);
}
