package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 영속성 컨텍스트 생성

        // 트랜잭션 얻어옴
        EntityTransaction tx = em.getTransaction();

        // 트랜잭션 시작
        tx.begin();

        try {
            // Member 저장
            Member member = em.find(Member.class, 1L);
//            Member member_agian = em.find(Member.class, 1L);
            member.setName("dirty checking!");
//            List<Member> findMembers = em.createQuery("select m from Member m", Member.class).getResultList();
            // commit
            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 만약 commit전 문제가 생기면 rollback
        } finally {
            em.close();
        }

//        // Member 저장
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("hello-jpa");
//        em.persist(member);
//
//        // 트랜잭션 commit
//        tx.commit();
//
//        em.close();
        emf.close();
    }
}
