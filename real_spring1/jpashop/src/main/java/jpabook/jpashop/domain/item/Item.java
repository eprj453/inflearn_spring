package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype") // 구분자 역할
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // ==== 비즈니즈 로직 ==== //
    // setter를 가지고 값을 가져오거나 변경하지 말고, 객체 안에서 값 변경이 이루어지도록 비즈니즈 로직을 짜는 것이 좋다.
    // 객체지향적이고, 응집력도 높아진다.

    // 재고수량 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고수량 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고는 0 이하일 수 없습니다");
        } else {
            this.stockQuantity = restStock;
        }
    }

    public void change(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        if (stockQuantity < 0) {
            throw new NotEnoughStockException("재고를 0 이하로 변경할 수 없습니다.");
        } else {
            this.stockQuantity = stockQuantity;
        }
    }




}
