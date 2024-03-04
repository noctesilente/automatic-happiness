package com.ohgiraffers.userservice.conttroller;

import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.vo.HelloVO;
import com.ohgiraffers.userservice.vo.RequestUser;
import com.ohgiraffers.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private Environment env;

    private HelloVO helloVo;

    private ModelMapper modelMapper;

    @Autowired
    public UserController(Environment env, HelloVO helloVo, ModelMapper modelMapper) {
        this.env = env;
        this.helloVo = helloVo;
        this.modelMapper = modelMapper;
    }

    /* 설명.
     *  application.yml 파일로부터 설정 값을 불러오기 위해서는 두 가지 방법이 제공된다.
     *  1. Environment를 의존성 주입 받아  getProperty로 설정 키 값을 작성해 불러오는 방법
     *  2. @Value를 활용해 필드로 주입 받고 활용하는 방법
     * */

    /* 설명. 1. Environment 활용해 설정값 불러오기(getProperty) */
    @GetMapping("/health_check")
    public String status() {
        return "Server at " + env.getProperty("local.server.port")
                + ", swcamp.message: " + env.getProperty("swcamp.message");
    }

    /* 설명. 2. @Value 활용해 설정값 불러오기(getter) */
    @GetMapping("/do_msa")
    public String doMsa() {
        return helloVo.getMessage();
    }


    // 본격적인 User 개발
    /* 설명. 회원가입(POST - /users) */
    // post 요청을 통해 하게 됨
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> registUser(@RequestBody RequestUser user) {
        // 성공한 회원에 대한 정보를 화면에 뿌려지도록 작성할 것 - 이번에는

        // modelmapper를 통해 requestuser를 userdto로 바꾸고 싶은 것
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        // 값 잘 들어갔는지 찍어보기
        System.out.println("userDTO = " + userDTO);

//        System.out.println("user = " + user);

        ResponseUser responseUser = new ResponseUser();
        responseUser.setName("응답잘됨");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);

    }


}
