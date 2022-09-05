package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // order 저장
    public void save(Order order) {
        em.persist(order);
    }

    // 단건조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 리스트 조회 (검색기능)
    public List<Order> findAllByString(OrderSearch orderSearch){
        // 만약에 값이 없으면 다 가져와야 하는데 값이 없으면 jpql "select o from Order o join o.member m"로 변경되야 한다 (동적쿼리)
        // 방법 1. 동적으로 생성 (강사는 잘 안씀) 실무에서 이렇게 안함. 버그 찾기도 힘들다. 버그가 충분히 발생할 수 있다.
        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색 (텍스트에 값이 있으면)
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    // 방법 2번째. 표준 동적쿼리를 build해주는 JPQL을 자바 코드로 작성하게끔 JPA에서 표준으로 제공하는 방법. (권장 X)
    // 실무에서도 안쓰고 실무에서 쓰라고 만든게 아니라고봄.
    // 치명적인 단점 : 유지보수성이 제로. 그래서 안씀. 이런식으로 할수 있다 정도만 보여줄려고 한것.
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Object> m = o.join("member", JoinType.INNER); //회원과 조인

        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }
    // 정답은 querydsl로 작성하기 -> 개발을 깔끔하게 할 수 있음.
}
