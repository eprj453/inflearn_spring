package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*; // @Entity, @Table,
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue // Id가 되는 값,
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") // 연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id") // 연관관계의 주인.
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER / CANCEL]

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // ==== 생성 메서드 ====//

    // 주문 생성에 관련된 비즈니스 로직을 모두 한 곳으로 응집시킴.
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem: orderItems) {
            order.addOrderItems(orderItem);
        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // ==== 비즈니즈 로직 ==== //

    /*
    * 주문 취소
    * */
    public void cancel() {
        // 만약 배송이 시작됐다면 취소불가능
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 시작된 상품입니다.");
        }

        this.setStatus(OrderStatus.CANCEL);

        for (OrderItem orderItem: orderItems) {
            orderItem.cancel();
        }
    }

    /*
    * 전체 주문가격 조회
    * */

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem: orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;

        //        return orderItems.stream()
//                .mapToInt(OrderItem::getTotalPrice)
//                .sum();


    }

}
