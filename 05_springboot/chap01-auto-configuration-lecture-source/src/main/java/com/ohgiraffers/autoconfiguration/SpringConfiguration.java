package com.ohgiraffers.autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringConfiguration {
    // 설정용 클래스

    @Value("${test.value}")
    // application.properties로부터 불러옴
    public String value;

    @Value("${username}")
    public String name;

    @Value("${test.age}")
    public int age;

    // , 를 기준으로 따로따로 받아서 배열이 가능한지
    @Value("${test.array}")
//    public List<String> list;
    public String[] arr;

    @Bean
    public Object propertyReadTest() {
        System.out.println("value = " + value);
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        System.out.println("======== 설정에서 읽어들여 여러 값 처리 ========");
//        list.forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);

        return new Object();
    }
    // 결과:
    // value = hell world!
    // name = B
    // age = 10
    // ======== 설정에서 읽어들여 여러 값 처리 ========
    // 안녕
    // 반가워

    // 실행하면 Chap01AutoConfigurationLectureSourceApplication이 실행되며
    // 8080 포트의 서블릿 컨테이너가 생성됨(서블릿이 켜짐), 별도로 빈을 관리하는 스프링 컨테이너도 생성됨
    // 서블릿 컨테이너에는 서블릿 객체들이, 스프링 컨테이너에는 빈들이 있음 -> 둘이 다름!
    // 스프링 컨테이너는 설정값을 읽고 컨테이너를 만듦 -> application.properties랑 Configuration을 읽고 만드는 것
    // propertyReadTest라는 이름의 콩을 만들고 출력한 후 객체를 만들고 그게 콩이 됨
    // 객체(return new Object())는 상관이 없고 읽어와서 변수에 담아서 콩이 만든 것은 앎= 출력이 가능?
    // 출력이 되는 시점: 콩이 만들어지는 시점 - 콩 만들 때 출력이 된 것
    // 콩도 만들고 컨테이너는 두 개가 생성이 됨 - 서블릿 컨테이너는 비어있나??


}
