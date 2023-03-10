package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
//    @Column(name = "price")   // 이름 같으면 생략 가능
    private Integer price;

//    @Column(name = "quantity")
    private Integer quantity;

    public Item() { // jpa 스펙에 이렇게 되어 있다.
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
