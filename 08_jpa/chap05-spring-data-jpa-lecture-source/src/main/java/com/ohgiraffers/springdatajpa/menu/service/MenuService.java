package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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


    /* 설명. 1. findById 예제 */
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

    /* 설명. 2. findAll(페이징 처리 전) */
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




}
