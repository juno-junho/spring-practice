package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;  // 주문 수량

    //JPA에서 protected 까지 기본생성자 만들수 있게 허용해줌
    // JPA에서 protected면 쓰지 말라는 말
//    protected OrderItem(){
//
//    }

    // == 생성 메소드 == // item에 가격있으니 그 가격을 orderPrice로 하면 되지 않나? -> 쿠폰값으로 할인되거나 변경될수 있기 때문에.
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        
        // 재고 까주기
        item.removeStock(count);
        return orderItem;
    }

    // == 비즈니스 로직 == //
    public void cancel() {
        // 재고수량을 원복해준다.
        getItem().addStock(getCount());
    }

    // == 조회로직 == //
    /** 주문 상품 전체 가격 조회*/
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
