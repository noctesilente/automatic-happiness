package com.ohgiraffers.common;

/* 설명.
 *  DAO 클래스는 Data Access Object를 줄인 말로 Repository 계층과 마찬가지로 java application과
 *  Database를 연동시켜 주기 위한 계층의 클래스로 활용된다.
* */

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/* 설명. DB와 연동하기 위해 사용되는 클래스에 추가하는 어노테이션으로 bean으로 관리될 수 있게 어노테이션을 추가한다. */
// 붙이면 기능이 특별한 게 있는 게 아니라 데이터베이스에서 문제가 생기면 자바에 exception 클래스를 만들어서 던져줌
// @Component가 달려있어도 콩으로 관리가 되기 때문에 Repository는 Component의 추가 기능이라고 생각하면 됨
// Component인데 뭔가 좀 더 추가로 있는 것 = Repository
@Repository

/* 설명. 클래스에 계층의 의미가 없이(추가적인 기능 없이) 단순히 bean으로 관리될 클래스는 @Component를 붙일 수 있다. */
// 클래스의 객체를 관리해줌
// 계층별로 특별한 기능을 달고 싶으면 @ ~ 를 달긴 하지만 그것들 다 내부적으로 Component를 지니고 있음
//@Component 가 달려있으면 다 빈으로 관리가 됨
//@Component
public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();

        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(2, new MemberDTO(2, "user02", "pass02", "유관순"));
    }

    /* 설명. 회원 조회용 메소드 */
    public MemberDTO selectMember(int sequence) {
        return memberMap.get(sequence);
    }

    /* 설명. 회원 가입용 메소드 */
    // DML 작업이 일어나면 몇 번 일어났는지 숫자로 발생
    public int insertMember(MemberDTO newMember) {
        int before = memberMap.size();

        memberMap.put(newMember.getSequence(), newMember);

        int after = memberMap.size();

        return after = before;
    }

}
