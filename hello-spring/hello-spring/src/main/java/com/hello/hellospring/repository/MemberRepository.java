package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 인터페이스로 구현해놓은 뒤 클래스(구현체)에서 갖다 쓰기
public interface MemberRepository {

    Member save(Member member);

    // Optional로 반환시 에러가 나도 조치가 취해진다
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll();
}
