package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.Order;

import java.util.List;

public interface CustomOrderRepository {

    public List<Order> serarch(OrderSearch orderSearch);
}
