package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional//변경하는것이기때문
    public Long order(Long memberId, Long itemId, int count) {
        // 주문할때 member의 id, item의 id, 수량 넘어옴
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성 (회원에 있는 address 넣기) 실제로는 배송지 정보 따로 입력.
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성 -> new Order()해서 set해서 생성하는것 막게끔 @NoArgsConstructor 설정하기.
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order); // 이것 하나만 한 이유 : cascade option때문에 그럼. order를 persist하면 orderItem, Delivery 다 persist.
        // cascade 범위에 대해서 명확하게 칼로 자르긴 애매하지만, lifecycle에 대해서 동일하게 (note 정리한것 보기)
        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel(); // jpa의 엄청 큰 장점 : entity안에데이터만 바꿔주면 jpa가 알아서 변경 포인트들을 dirty checking(변경된 감지)
        // 해서 변경된 내역들을 찾아서 db에 update 쿼리 다 날라감.
    }

    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//
//    }
}
