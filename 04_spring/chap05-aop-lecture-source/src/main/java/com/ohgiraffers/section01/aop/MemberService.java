package com.ohgiraffers.section01.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberDAO memberDAO;
    // 주입 받은 후 바뀌면 안 되기 때문에 final
    // final을 쓰기 위해서 생성자 주입을 받을 것
    // 주소값이 안 바뀌는 거지 안은 바뀔 수 있음

    // 자동으로 달리지만 Autowired 달아주기
    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    // 이게 primary concern 주요 기능
    // 여기에 로그를 찍기 위해서 sout를 하면 기능을 직접 건드리는 거니까
    // 이건 그대로 두고 보호막을 씌우는 걸 proxy라고 함
    // 공통적으로 쓸 기능을 따로 만드는 것을 aspect라고 함

    public List<MemberDTO> findAllMembers() {
        System.out.println("target -> findAllMembers 실행");
        return memberDAO.selectAllMembers();
    }

    public MemberDTO findMemberBy(int index) {
        System.out.println("target -> findAllMemberBy 실행");
        return memberDAO.selectMemberBy(index);
    }
}
