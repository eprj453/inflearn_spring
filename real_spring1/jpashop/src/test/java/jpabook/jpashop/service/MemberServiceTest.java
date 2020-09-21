package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트를 띄운 상태로(컨테이너가 떠있는 상태로) 테스트하기 위함
@Transactional // 테스트가 끝난 이후에 rollback을 한다.
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("jack");

        // when
        Long savedId = memberService.join(member);
        assertEquals(member, memberRepository.findOne(savedId));

        // then
    }

    @Test
    public void 중복_회원_검증() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("jack1");

        Member member2 = new Member();
        member2.setName("jack1");


        // when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
//        memberService.join(member2);

        // then
        fail("터져야 하는 예외가 터지지 않음!");
        // 이전에 exception이 터져서 여기까지 코드가 오면 안되는데, 올 경우에는 fail 메서드로 메서드의 정상작동 여부를 확인.
    }

}