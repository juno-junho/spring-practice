package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static jpabook.jpashop.repository.refactored.OrderSpec.memberNameLike;
import static jpabook.jpashop.repository.refactored.OrderSpec.orderStatusEq;

@Getter @Setter
public class OrderSearch {

    private String memberName;// 회원 이름
    private OrderStatus orderStatus; // 주문 상태 [ORDERM CANCEL]

    public Specification<Order> toSpecification() {
        return Specification.where(memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }
}
