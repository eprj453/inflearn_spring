package com.hello.hellospring.controller;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createForm";
    }

    @PostMapping("/members/join")
    public String joinMember(CreateForm createform) {
        Member m = new Member();
        m.setName(createform.getName());

        System.out.println("member  : "+createform.getName());
        memberService.join(m);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("members", memberList);
        return "/members/memberList";
    }
}
