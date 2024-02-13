package com.ohgiraffers.common;

public class PersonalAccount implements Account {
// interface를 받았으니 오버라이딩을 해줘야 함

    private final int bankCode;         // 은행 코드
    private final String accNo;         // 계좌 번호
    private int balance;                // 잔액

    public PersonalAccount(int bankCode, String accNo) {
        this.bankCode = bankCode;
        this.accNo = accNo;
    }
    // final이 붙어있으면 객체가 만들어지기 전까지 초기화가 되어야 함 아니면 오류가 뜸
    // 이 생성자 만들어지기 전까지 bankCode랑 accNo는 전부 에러가 뜸
    // 아니면 앞서서 bankCode = 0; 이런 식으로 해줘야 함
    // 생성자를 만들어주면 어떤 값이 넘어와서 되는 거니까 에러가 안 뜸


    @Override
    public String getBalance() {
        return this.accNo + "계좌의 현재 잔액은 " + this.balance + "원입니다.";
    }

    @Override
    public String deposit(int money) {

        String str = "";

        if (money >= 0) {
            this.balance += money;
            str = money + "원이 입금되었습니다.";
        } else {
            str = "금액을 잘못 입력하셨습니다.";
        }
        return str;
    }

    @Override
    public String withdraw(int money) {

        String str = "";

        if (this.balance >= money) {
            this.balance -= money;
            str = money + "원이 출금되었습니다.";
        } else {
            str = "잔액이 부족합니다. 잔액을 확인해 주세요.";
        }

        return str;
    }
}
