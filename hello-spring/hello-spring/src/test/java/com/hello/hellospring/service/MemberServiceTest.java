package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 여기서 memberRepository를 new로 생성해서 쓰는 그림이 좋지 않다.
    // 물론 MemoryMemberRepository 클래스에서 static으로 선언되어 있기 때문에 현재 결과에는 영향을 주지 않지만 구조적으로 좋지 않다.
    // 그렇기 때문에 MemberService 클래스의 구조를 조금 변경할 필요가 있다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }




    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member m = new Member();
        m.setName("hello-test");

        // when
        Long saveId = memberService.join(m);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(m.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원검증() { // 예외 상황 검증
        Member m1 = new Member();

        m1.setName("spring-member1");
        memberService.join(m1);
        Member m2 = new Member();
        m2.setName("spring-member1");

        // memberService에 m2를 join했을때 IllegalStateException이 터져야함.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));

        assertThat(e.getMessage()).isEqualTo("이미 존재합니다.");
//        try{
//            memberService.join(m2);
//        } catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재합니다.123123");
//        }


    }


    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}