package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);


        Member findMember = memberService.findMember(1L);
        System.out.println("new member : "+member.getName());
        System.out.println("find member : "+findMember.getName()); // 눈으로 직접 확인하는 테스트 과정.

    }
}
