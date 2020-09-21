package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링이 entity manager를 만들어서 injection해준다.
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) { // 단건조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // 전부 찾는 메서드는 JPQL 직접 작성해야한다.
        // sql과 거의 동일하지만, table을 대상으로 쿼리를 날리는 sql과는 달리
        // JPQL은 Entity 객체를 대상으로 쿼리를 날린다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
