package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 컴포넌트 스캔(Service)
@Transactional(readOnly = true)
// @AllArgsConstructor // lombok 어노테이션, 적용 시 필드 변수의 모든 생성자를 만들어준다.
@RequiredArgsConstructor // final이 있는 필드 변수의 생성자를 만들어준다.

// ★ JPA의 데이터 변경이나 로직들은 트랜잭션 안에서 실행되는 것이 바람직하다.
// SELECT 쿼리문을 날리는 경우에는 readonly true를 줘서 성능에 이점을 가져오도록 한다.
// 클래스 내부를 기본적으로 readonly true를 준 뒤에 SELECT 날리는 메서드가 아닌 경우에는 Transactional을 따로 설정한다.
public class MemberService {

    // field injection의 문제점 : 테스트 등 변경 필요시 변경 자체가 매우 어려워진다.
    // @Autowired // 스프링이 스프링 빈에 등록되어 있는 memberRepository를 injection 해준다.
    // private MemberRepository memberRepository;

    // setter injection의 문제점 : 트랜잭션 중간에 누군가가 setter로 들어와버리면 로직이 꼬일 수 있다.
    // public void setMemberRepository(MemberReposirtory memberRepository) {
    //  this.memberRepository = repository
    // }

    // 생성자 injection
    // 테스트케이스 등 작성할때
    // MemberService memberService = new MemberService(moc 데이터 등);
    //
    private final MemberRepository memberRepository; // final을 넣어주면 값 세팅 여부를 컴파일 시 체크할 수 있다.

//    @Autowired // 생성자가 1개밖에 없는 경우에는 @Autowired가 없어도 스프링이 자동으로 생성자에 injection해준다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    // 이렇게 검증할 수 있겠지만, 멀티 스레드 환경에서 같은 이름이 동시에 insert되면 중복이 될 수도 있다.
    // 중복을 피하고자 할 경우에는 db에서도 unique 조건을 걸어서 방어선을 마련해주는 것이 바람직하다.
    private void validateDuplicateMember(Member member) {
        // 중복 시 exception 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원 조희
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원(1명) 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    
}
