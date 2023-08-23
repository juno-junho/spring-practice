package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepositoryRefactored extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}
