package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; // 주문가격
    private int count; // 주문 수량

    // ==== 생성 메서드 ==== //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 상품에서 orderItem의 count만큼 갯수를 빼준다.
        item.removeStock(count);
        return orderItem;
    }



    // ==== 비즈니즈 로직 ==== //
    public void cancel() {
        getItem().addStock(count); // 재고 원복
    }

    public int getTotalPrice() { // 상품의 주문가격 * 주문수량
        return getOrderPrice() * getCount();
    }



}
