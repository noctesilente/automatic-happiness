package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagination;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/menu")
@Slf4j              // log 찍는 용도로라도 집어넣기
public class MenuController {

    private final MenuService menuService;


    /* 설명. 로그 생성해 보기 */
    // log 찍기 - 1. lombok 2. logger
//    Logger logger = LoggerFactory.getLogger(MenuController.class);
    // 더 쉬운 방법
//    Logger logger = LoggerFactory.getLogger(getClass());

    // lombok 사용
    // 클래스 위에 @Slf4j 붙이기

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    /* 설명. 1. 7번 메뉴 목록 보기 */
    @GetMapping("/{menuCode}")          // path valuable 방식 - 경로 상에 있는 걸 변수로 받아내는 방식
    // 링크가 http://localhost:8888/menu/7 로 뜸
    public String findMenuByCode(@PathVariable int menuCode, Model model)  {
//       logger.info("menuCode: {}", menuCode);       // 두번째에 있는 매개변수가 왼쪽 중괄호에 들어감
        // 결과: 2024-02-28T09:41:26.336+09:00  INFO 32708 --- [nio-8888-exec-1] c.o.s.menu.controller.MenuController     : menuCode: 7
        // 이렇게 뜸

        // lombok으로 log 찍기
        log.info("menuCode: {}", menuCode);

        MenuDTO menu = menuService.findMenuByCode(menuCode);
        model.addAttribute("menu", menu);

        return "menu/detail";
    }


    /* 설명. 2. 메뉴 전체 목록 보기 - 페이징 처리 전 */
//    @GetMapping("/list")
//    public String findMenuList(Model model) {
//
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);
//        // list.html이랑 이름 맞춰줘야 함
//
//        return "menu/list";
//    }

    /* 설명. 3. 메뉴 전체 목록 보기 - 페이징 처리 후 */
    @GetMapping("/list")
    public String findMenuList(@PageableDefault Pageable pageable, Model model) {
        log.info("pageable: {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);
        // 기존에 만들었던 메소드가 매개변수가 없는 것이었기 때문에 빨간 줄 발생
        // service 가서 만들어주기

        log.info("조회한 내용 목록: {}", menuList.getContent());
        log.info("총 페이지 수: {}", menuList.getTotalPages());
        log.info("총 메뉴 수: {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시될 요소 수: {}", menuList.getSize());
        log.info("해당 페이지에 실제 요소 수: {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부: {}", menuList.isFirst());
        log.info("마지막 페이지 여부: {}", menuList.isLast());
        log.info("정렬 방식: {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스: {}", menuList.getNumber());

        /* 설명. 화면에서 버튼을 그리기 위해 필요한 재료 준비(클래스(모듈화) 두 개 추가) */
        PagingButtonInfo paging = Pagination.getPagingButtonInfo(menuList);

        model.addAttribute("paging", paging);
        model.addAttribute("menuList", menuList);

        return "menu/list";

        // 결과:
        // 2024-02-28T11:44:03.758+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     :
        // 조회한 내용 목록: [MenuDTO(menuCode=23, menuName=꿀발린추어탕, menuPrice=7000, categoryCode=4, orderableStatus=Y), MenuDTO(menuCode=21, menuName=돌미나리백설기, menuPrice=5000, categoryCode=11, orderableStatus=N), MenuDTO(menuCode=20, menuName=마라깐쇼한라봉, menuPrice=22000, categoryCode=5, orderableStatus=N), MenuDTO(menuCode=19, menuName=까나리코코넛쥬스, menuPrice=9000, categoryCode=9, orderableStatus=Y), MenuDTO(menuCode=18, menuName=붕어빵초밥, menuPrice=35000, categoryCode=6, orderableStatus=Y), MenuDTO(menuCode=17, menuName=아이스가리비관자육수, menuPrice=6000, categoryCode=10, orderableStatus=Y), MenuDTO(menuCode=16, menuName=흑마늘아메리카노, menuPrice=9000, categoryCode=8, orderableStatus=Y), MenuDTO(menuCode=15, menuName=죽방멸치튀김우동, menuPrice=11000, categoryCode=6, orderableStatus=N), MenuDTO(menuCode=14, menuName=과메기커틀릿, menuPrice=13000, categoryCode=6, orderableStatus=Y), MenuDTO(menuCode=13, menuName=직화구이젤라또, menuPrice=8000, categoryCode=12, orderableStatus=Y)]
        // 2024-02-28T11:44:03.764+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 총 페이지 수: 3
        // 2024-02-28T11:44:03.768+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 총 메뉴 수: 21
        // 2024-02-28T11:44:03.769+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 해당 페이지에 표시될 요소 수: 10
        // 2024-02-28T11:44:03.771+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 해당 페이지에 실제 요소 수: 10
        // 2024-02-28T11:44:03.772+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 첫 페이지 여부: true
        // 2024-02-28T11:44:03.774+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 마지막 페이지 여부: false
        // 2024-02-28T11:44:03.775+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 정렬 방식: menuCode: DESC
        // 2024-02-28T11:44:03.775+09:00  INFO 13544 --- [nio-8888-exec-4] c.o.s.menu.controller.MenuController     : 여러 페이지 중 현재 인덱스: 0

    }


    /* 설명. 4. 메뉴 가격으로 조회 - 입력 가격을 초과하는 메뉴의 목록 조회 */
    @GetMapping("/querymethod")
    public void queryMethodPage() {
    }

    @GetMapping("/search")
    public String findMenuPrice(@RequestParam int menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        return "/menu/searchResult";
    }


    /* 설명. 5. 카테고리 고를 수 있게 출력 */
    @GetMapping("/regist")
    public void registPage() {
    }

    /* 설명. /menu/regist.html이 열리자마자 js 코드를 통해 /menu/category 비동기 요청이 오게 된다. */
    // 요청을 받아줄 핸들러 메소드 만들기
    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    /* 설명. 메소드에 @ResponseBody가 붙은 메소드의 반환형은 ViewResolver가 해석하지 않는다. */
    /* 설명.
     *  @ResponseBody가 붙었을 때 기존과 다른 핸들러 메소드의 차이점
     *  1. 핸들러 메소드의 반환형이 어떤 것이라도 상관없다. (-> 모두 json 문자열 형태로 요청이 들어온 곳으로 반환된다.)
     *  2. 한글이 포함된 데이터는 produces 속성에 'application/json'라는 MIME 타입과 'charset=UTF-8' 인코딩 타입을 붙여준다.
     * */
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
//        @ResponseBody - 요청이 들어온 애한테 문자열을 준다는 개념 - 뷰를 고른다는 개념 X 뷰 리졸버 동작 X

        return menuService.findAllCategory();
    }


    /* 설명. 6. INSERT */
    /* 설명. Spring Data JPA로 DML 작업하기(Insert, Update, Delete) */
    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu) {
        menuService.registMenu(newMenu);

        return "redirect:/menu/list";
    }


    /* 설명. 7. UPDATE */
    @GetMapping("/modify")
    public void modifyPage() {
    }

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenu) {
        menuService.modifyMenu(modifyMenu);

        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }


    /* 설명. 8. DELETE */
    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode) {
        menuService.deleteMenu(menuCode);

        // 삭제된 걸 볼 순 없으니까 전체 메뉴를 봐서 삭제됐는지 확인
        return "redirect:/menu/list";
    }
}
