package com.hello.hellospring.service;


import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }


    //    @AfterEach
    //    public void afterEach() {
    //        memberRepository.clearStore();
    //    }

    @Test
//    @Commit // transactional에 의해 롤백되는 것을 방지하고 DB 반영을 COMMIT
    void 회원가입() {
        // given
        Member m = new Member();
        m.setName("hello-test3");

        // when
        Long saveId = memberService.join(m);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(m.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원검증() throws Exception { // 예외 상황 검증
        Member m1 = new Member();

        m1.setName("spring-member1");
        memberService.join(m1);
        Member m2 = new Member();
        m2.setName("spring-member1");

        // memberService에 m2를 join했을때 IllegalStateException이 터져야함.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));

        assertThat(e.getMessage()).isEqualTo("이미 존재합니다.");
    }


    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}
