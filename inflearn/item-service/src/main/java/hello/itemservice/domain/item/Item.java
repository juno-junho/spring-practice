package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
//@Data // 핵심 도메인에 쓰기 되게 위험함. getter, setter 정도만 쓰기. dto 같은 경우는 써도 괜찮음
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;  // null로 들어갈 가능성이 있다.
    private Integer quantity;   // null로 들어갈 가능성이 있다.(상황에 맞게 하면됨)

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
