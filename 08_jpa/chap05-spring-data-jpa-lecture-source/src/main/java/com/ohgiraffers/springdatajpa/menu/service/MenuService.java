package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/* 설명.
 *  Service 계층: 비즈니스 로직, 트랜잭션 처리(@Transactional), DTO <-> Entity(modelmapper lib 활용)
 * */
@Service
public class MenuService {

    private final ModelMapper mapper;

    private final MenuRepository menuRepository;

    private final CategoryRepository categoryRepository;

    public MenuService(ModelMapper mapper, MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }


    /* 설명. 1. findById 예제 - 7번 메뉴 목록 보기 */
    // 조회를 한 거기 때문에 커밋, 롤백을 할 필요기 없기 때문에 @Transactional를 쓸 필요가 없음
    public MenuDTO findMenuByCode(int menuCode) {
        // JPaRepository가 물려준 메소드 -> 반환형이 optional임 -> 제네릭으로 걸었던 타입으로 다시 돌려줌
        // Optional<Menu> findById(Integer param) -> 이 만들어진 것

        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        // orElseThrow: null이 넘어왔을 때 어떤 결과가 나올지 세팅 가능 -> IllegalArgumentException 사용할 것


        // mapper 사용 말고 필드 하나하나 옮길 거면
//        MenuDTO menuDTO = new MenuDTO();
//        menuDTO.setMenuCode(menu.getMenuCode());
//        menuDTO.setMenuName(menu.getMenuName());
//        menuDTO.setMenuPrice(menu.getMenuPrice());
        // 등등 이런 식으로 필요로 하는 값을 옮겨 담아서 던져주기 -> 이걸 메소드로 만들어서 던져주기?

        // Service는 DTO<->Entity 역할도 하기 때문에 mapper 사용
        return mapper.map(menu, MenuDTO.class);
    }

    /* 설명. 2. 메뉴 전체 목록 보기 - findAll(페이징 처리 전) */
    public List<MenuDTO> findMenuList() {

        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());
        // ascending, descending 해줄 수 있음
        // 이런 식으로 정렬 가능
        // menu 엔티티 하나씩 꺼낸 다음 람다식을 이용해서 menu를 MenuDTO로 하나씩 바꿔줌
        // entity -> DTO, Menu -> MenuDTO 끼리 한 다음에 List로 바꿔줌
        // 즉 이것이 List<MenuDTO>가 되는 것
        // Memo. List를 하나씩 꺼내어 Map에 넣는다. -> 메뉴를 하나씩 DTO 형태로 쌓는다. -> DTO를 List형태로 변환한다.
        return menuList.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());

    }

    /* 설명. 3. 메뉴 전체 목록 보기 - findAll(페이징 처리 후) */
    public Page<MenuDTO> findMenuList(Pageable pageable) {

        /* 설명.
         *  1. 넘어온 pageable에 담긴 페이지 번호를 인덱스 개념으로 바꿔서 인지시킴
         *  2. 한 페이지에 뿌려질 데이터 크기
         *  3. 정렬 기준
        * */

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                                  // 삼항 연산자가 하는 일 = 인덱스 체계로 바꾸는 것, 음수 또는 0이 되면 0으로
                                  pageable.getPageSize(),
                                  Sort.by("menuCode").descending());

        Page<Menu> menuList = menuRepository.findAll(pageable);
        // 그 페이지에 맞는 게시물 10개를 뽑아서 줌

        return menuList.map(menu -> mapper.map(menu, MenuDTO.class));

    }


    /* 설명. 4. 메뉴 가격으로 조회 - 입력 가격을 초과하는 메뉴의 목록 조회 */
    public List<MenuDTO> findMenuPrice(int menuPrice) {

        /* 설명. 전달 받은 가격을 초과하는 메뉴의 목록을 조회하는 메소드 */
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);
        // 이런 식으로 메소드 이름 잘 지으면 쿼리 자동으로 생성해줌 -> findById 같은 건 자체적으로 있는 거라 굳이 Repository에 선언 X -> 이미 부모 클래스에 있음
        // 이건 자식 클래스가 추가 구현한 거라서 Repository에 들어감
        // 다중행이라 List

        return menuList.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());

    }

    /* 설명. 5. 카테고리 고를 수 있게 출력 */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    /* 설명. 6. INSERT */
    @Transactional      // DML 작업
    public void registMenu(MenuDTO newMenu) {
        menuRepository.save(mapper.map(newMenu, Menu.class));

    }

    /* 설명. 7. UPDATE */
    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {

        // 수정할 거 가져오기 - PK 값으로 가져오고 없으면 예외 발생시키기
        Menu foundMenu = menuRepository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalAccessError::new);

        foundMenu.setMenuName(modifyMenu.getMenuName());
        // 업데이트가 작성됨

    }

    /* 설명. 8. DELETE */
    @Transactional
    public void deleteMenu(int menuCode) {

        menuRepository.deleteById(menuCode);
        // repository 가 알고 있는 제네릭의 pk 와 같은 타입이면 pk로 인지하고 지워줌
        // extends 하고 썼던 제네릭
    }
}
