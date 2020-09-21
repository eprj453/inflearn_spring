package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    // ==== 상품(Item) 공통속성 ==== //
    private Long id; // 상품 수정이 있기 때문에 id값이 필요하다.
    private String name;
    private int price;
    private int stockQuantity;

    // ==== 책(Book) 속성 ==== //
    private String author;
    private String isbn;
}
