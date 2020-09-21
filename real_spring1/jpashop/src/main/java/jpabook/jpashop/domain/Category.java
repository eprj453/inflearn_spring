package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Item> items = new ArrayList<>(); // 다대다 관계를 표현하기 위한 매핑 테이블

    // 다대다 관계를 표현하는데에는 ManyToMany 테이블은 잘 쓰지 않는다.
    // 이정도의 간단한 매핑은 가능하지만, 중간에 다른 값이 들어오거나 하는게 불가능하기 때문이다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    // 연관관계를 설정해주는 메서드 (양방향으로 연결되어 있는 경우.)
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }


}
