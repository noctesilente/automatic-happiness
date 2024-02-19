package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {

    @Select("SELECT NOW()")
    Date selectNow();

    // 실제 서비스를 해야 될 것을 생각해보면 이 안에 쿼리문을 넣는 건... 너무 힘듦
    //
}
