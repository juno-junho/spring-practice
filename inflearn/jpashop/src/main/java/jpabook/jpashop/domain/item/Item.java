package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
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
@Setter // setter를 가지고 변경하는것이 아니라 비즈니스 로직을 가지고 변경해야 한다.
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

    // == 비즈니스 로직 == //
    /**
     *재고를 늘리고 줄이기 (stock)
     * 보통 itemService에서 stockQuantity 가지고 와서 값을 더해서 넣고 값을 만든 다음 item.setQuantity에서 결과를 넣는 쪽으로 개발을 많이 했을 것이다.
     * 하지만 객체지향적으로 생각해보면 데이터를 가지고 있는쪽에 비즈니스 메소드가 있는것이 가장 좋다. 그래야 응집력이 있다.
     * 여기 있는것이 관리하기가 좋다.
      */

    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0) throw new NotEnoughStockException("need more stock");
        this.stockQuantity = restStock;
    }

}
