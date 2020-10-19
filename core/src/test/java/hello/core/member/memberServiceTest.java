package hello.core.member;

import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class memberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void 회원가입() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertEquals(member, findMember);
    }
}
