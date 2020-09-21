package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em; //@RequiredArgsConstructor 덕분에 Autowired 할 필요가 없음

    public void save(Item item) {
        if(item.getId() == null) { // 새로 생성하는 객체면
            em.persist(item);
        } else { // 그렇지 않으면
            em.merge(item); // 이미 등록되어 있는 객체와 병합
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
