package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.UpdateItemDto;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // 영속성 컨텍스트가 자동으로 변경된 사항을 반영해 update 쿼리까지 날려준다. 정보 변경시 em.merge는 최대한 사용하지 않고 변경사항만 감지해 변경하도록 하자.
    public void update(Long itemId, UpdateItemDto dto) {
        Item findItem = itemRepository.findOne(itemId); // 트랜잭션 내에서 엔티티를 조화해야 영속 상태로 조회할 수 있고, 영속 상태의 엔티티를 변경하면 자동으로 update 쿼리까지 날려준다(dirty checking).
        findItem.change(dto.getName(), dto.getPrice(), dto.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long findId) {
        return itemRepository.findOne(findId);
    }
}
