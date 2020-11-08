package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인 적용")
    void vio_o() {
        // given
        Member m = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(m, 10000);

        // then
        assertEquals(discount, 1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 미적용!")
    void vio_x() {
        Member m = new Member(1L, "memberVIP", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(m, 10000);

        // then
        assertEquals(discount, 0);
    }

}