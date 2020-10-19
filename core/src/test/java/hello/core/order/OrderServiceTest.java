package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void 주문_생성() {

        // given
        Long memberId = 1L;
        Member member = new Member(1L, "testMemberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "testItemA", 10000);

        // then
        Assertions.assertEquals(order.getDiscountPrice(), 1000);
    }
}
