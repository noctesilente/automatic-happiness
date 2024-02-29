package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* 설명. RestController는 모든 핸들러 메소드에 @ResponseBody를 붙여주는 어노테이션이다. */
/* 설명. 이 Controller의 핸들러 메소드 반환값은 이제 viewResolver가 처리하지 않는다.(JSON 문자열로 반환된다.) */
@RestController
// rest api를 위해 만든 controller
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world!";
        // 요청한 곳으로 갈 뿐인 거지 방향이 없음 -> 편함
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("message")
    public Message getMessage() {
        return new Message(200, "메시지를 응답합니다.");     // 순수한 자바의 객체 -> 냅다 반환해도 상관없음
        // jackson2httpMessageConverter가 저절로 json 형태로 바꿔줌
        // 여기서 Message는 클래스

        // 자바의 객체가 json 문자열로 바뀐 것
    }

    /* 설명.
     *  @RestController에서 반환한 것(자바의 타입들)은 모두 JSONArray형태({}), 또는 JSONObject 형태({})로 바뀌어
     *  JSON 문자열로 반환한다.
     *  1. Map 또는 일반 클래스 타입 -> [] 형태
     *  2. ArrayList -> [] 형태
     *
     * 설명.
     *  JSON(JavaScript Object Notation) 자바스크립트가 인지할 수 있는 객체 형태
     * */


    @GetMapping("test")
    public List<Map<String, Object>> getTest() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("test1", new Message(200, "success1"));
        map.put("test2", new Message(200, "success2"));
        map.put("nextPageLink", "http://localhost:8080/hello");
        list.add(map);
        return list;
    }

    @GetMapping("/list")
    public List<String> getList() {
        return List.of(new String[]{"사과", "바나나", "복숭아 "});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMapping() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다."));
        messageList.add(new Message(500, "개발자의 잘못입니다."));

        return messageList.stream()
                .collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
    }


    // 이미 resources - static에 넣기
    /* 설명. 이미지 응답하기 */
    /* 설명. produces는 response header의 content-type 설정이다. */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/static/감자 망곰2.jpg").readAllBytes();
    }

    /* 설명. ResponseEntity는 필수는 아니지만 유용하게 사용할 수 있다. */
    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {
        return ResponseEntity.ok(new Message(200, "응답 성공"));
    }
}
