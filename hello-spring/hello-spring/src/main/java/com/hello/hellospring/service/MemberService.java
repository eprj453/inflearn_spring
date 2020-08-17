package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repo;

    @Autowired
    // MemberRepository를 new로 직접 생성하지 않고, 생성자로 외부에서 넣어주도록 구조를 변경한다.
    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public Long join(Member m) {
        validateDuplicateMember(m);
        repo.save(m);
        return m.getId();
    }

    public void validateDuplicateMember(Member m) { // optional 형태로 반환하기 때문에 이런 형태의 반환이 가능.
        repo.findByName(m.getName())
                .ifPresent(member -> {
                    throw new IllegalStateException("이미 존재합니다.");
                });
    }
    // 전체 회원 조회
    public List<Member> findAll() {
        return repo.findAll();
    }
    
    // id로 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return repo.findById(memberId);
    }
}
