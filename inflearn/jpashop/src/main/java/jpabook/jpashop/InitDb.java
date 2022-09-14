package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * USER A
 * * JPA1 BOOK
 * * JPA2 BOOK
 * USER B
 * * SPRING1 BOOK
 * * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {


    private final InitService initService;

    @PostConstruct // application 로딩 시점에 호출해 주고 싶어서 한것. (spring bean 다 올라오고 나면 spring이 호출해줌)
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        // 이안에 아래 코드 전부 붙혀넣을수도 있지만 스프링 라이프사이클 때문에 여기에 transaction 먹이고 하는게 잘 안된다.
        // 그래서 별도의 bean으로 등록하는 것이다.
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울", "1", "1111"));
            em.persist(member);

            Book book1 = Book.createBook("JPA1 BOOK", 10000, 100);
            Book book2 = Book.createBook("JPA2 BOOK", 20000, 100);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
        public void dbInit2() {
            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("거제", "2", "2222"));
            em.persist(member);

            Book book1 = Book.createBook("SPRING1 BOOK", 20000, 100);
            Book book2 = Book.createBook("SPRING2 BOOK", 40000, 100);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
