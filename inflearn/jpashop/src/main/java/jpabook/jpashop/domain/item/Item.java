package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 상속관계 전략을 지정해줘야함
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {    // 추상클래스로 만드는 이유 : 구현체를 가지고 할거기 때문

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    // 상속관계 매핑

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
