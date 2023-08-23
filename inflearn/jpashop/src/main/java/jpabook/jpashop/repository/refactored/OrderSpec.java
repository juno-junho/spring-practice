package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpec {

    public static Specification<Order> memberNameLike(final String memberName) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(final Root<Order> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
                if (memberName.isBlank()) {
                    return null;
                }
                Join<Object, Object> m = root.join("member", JoinType.INNER); // 회원과 조인
                return criteriaBuilder.like(m.get("name"), "%" + memberName + "%");
            }
        };
    }

    public static Specification<Order> orderStatusEq(final OrderStatus orderStatus) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(final Root<Order> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
                if (orderStatus == null) {
                    return null;
                }
                return criteriaBuilder.equal(root.get("status"), orderStatus);
            }
        };
    }
}
