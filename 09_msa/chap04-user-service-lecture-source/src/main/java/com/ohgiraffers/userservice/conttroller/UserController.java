package com.ohgiraffers.userservice.conttroller;

import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.service.UserService;
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

    private UserService userService;
    // 컨트롤러는 서비스의 타입만 보고 있고 실제로 동작하는 게 impl인 것을 모름 = psa 기술

    @Autowired
    public UserController(Environment env,
                          HelloVO helloVo,
                          ModelMapper modelMapper,
                          UserService userService) {
        // 처음에 인터페이스라서 빨간 줄이 뜸 -> 이 서비스를 상속받은 impl을 만든 다음 @Service 붙이면 해결됨
        this.env = env;
        this.helloVo = helloVo;
        this.modelMapper = modelMapper;
        this.userService = userService;
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

        // yml 파일 숨겨놓는 과정에서 증명을 위해 추가
        /* 설명. config server에서 제공하는 test.message 값 확인 */
        System.out.println("config sever의 설정값 확인: " + env.getProperty("test.message"));


        /* 설명. RequestUser -> UserDTO */
        // modelmapper를 통해 requestuser를 userdto로 바꾸고 싶은 것
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        // 값 잘 들어갔는지 찍어보기
//        System.out.println("userDTO = " + userDTO);

        /* 설명. 회원가입 비즈니스 로직 시작 */
        // 컨트롤러가 알고 있는 건 userservice니까 userservice에 구현됨 -> impl에도 구현해야 됨 -> alt + enter로 구현
        userService.registUser(userDTO);
        // userDTO는 객체가 아니라 주소값만 넘겼던 것 -> 객체는 한 개 -> userID랑 encryptedPwd가 비어있음 -> serviceimpl에서 이 객체를 봤을 때 이 두 개를 채워준 것
        // 모든 건 다 객체 하나에 관한 내용 -> 넘겨줬다고 새로운 객체가 되는 것이 아니라 userserviceimpl에서 set하면 controller에서 쓴 dto에서도 적용이 됨
        // -> call by reference

//        System.out.println("user = " + user);

        /* 설명. UserDTO -> ResponseUser */
        // 반환값 알아보기 - userdto 랑 responseuser 일치하는 필드에 aovlddl ehla
        ResponseUser responseUser = modelMapper.map(userDTO, ResponseUser.class);

//        ResponseUser responseUser = new ResponseUser();
//        responseUser.setName("응답잘됨");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("id") String id) {
        // 조회
        UserDTO userDTO = userService.getUserById(id);

        // 조회한 다음에 VO에 넣기
        ResponseUser returnValue = modelMapper.map(userDTO, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
