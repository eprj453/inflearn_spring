package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded // 내장 타입 어노테이션
    private Address address;

    @Enumerated(EnumType.STRING) // ordinary로 사용할 경우 매우 큰 장애를 만날 수 있다. STRING으로 쓰자.
    private DeliveryStatus status; // READY, COMP
}
