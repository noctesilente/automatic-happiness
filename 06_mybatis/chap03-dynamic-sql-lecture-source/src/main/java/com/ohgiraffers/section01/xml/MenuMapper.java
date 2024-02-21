package com.ohgiraffers.section01.xml;

import java.util.List;

public interface MenuMapper {

    /* 1-5 */
    List<MenuDTO> selectMenuByPrice(int maxPrice);
}
