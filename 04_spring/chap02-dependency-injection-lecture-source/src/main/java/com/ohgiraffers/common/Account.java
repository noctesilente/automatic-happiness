package com.ohgiraffers.common;

public interface Account {
    // 계좌라면 가져야 되는 공통적인 규약을 정의할 것

    /* 설명. 잔액 조회 */
    String getBalance();

    /* 설명. 입금 */
    String deposit(int money);

    /* 설명. 출금 */
    String withdraw(int money);
}
