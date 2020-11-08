package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.OrderService;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class memberServiceTest {
//    MemberService memberService = new MemberServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach // Test가 실행되기 전 무조건 실행되도록 하는 어노테이션
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

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
