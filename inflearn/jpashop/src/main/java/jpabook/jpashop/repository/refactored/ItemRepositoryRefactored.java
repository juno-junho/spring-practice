package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// QueryDsl 사용 하는 방법 1. QuerydslPredicateExecutor 사용 -> join, fetch 같은 기능 사용 불가능.
public interface ItemRepositoryRefactored extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {


}
