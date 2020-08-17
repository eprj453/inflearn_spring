package com.hello.hellospring.MemberTest;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach // 한번에 여러 테스트를 실행하면 메모리 db에 직전 테스트의 결과가 남을 수 있다.
    // 결과에 영향을 미칠 수 있으므로 @AfterEach 어노테이션을 통해 각 테스트가 실행될때마다 메서드를 실행한다.
    // 각 테스트는 독립적으로 움직일 수 있어야 한다.

    public void afterEach() {
        repo.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("hello-spring");

        // when
        repo.save(member);

        // then
        Member result = repo.findById(member.getId()).get();
        // Optional.get()
        // optional 객체에서 값을 가져오는데, 비어있는 객체에 대해서는 NoSuchElementException을 반환한다.

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // given
        Member m1 = new Member();
        m1.setName("spring1");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repo.save(m2);

        Member result = repo.findByName("spring1").get(); // repo에서 이름이 spring1인 사람을 가져옴
        assertThat(result).isEqualTo(m1);

    }

    @Test
    public void findAll() {
        Member m1 = new Member();
        m1.setName("spring1");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repo.save(m2);

        Member m3 = new Member();
        m3.setName("spring3");
        repo.save(m3);

        List<Member> memberList = repo.findAll();
        assertThat(memberList.size()).isEqualTo(3);
    }

}
