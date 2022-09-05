package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 저장
    public void save(Item item) {
        // item은 jpa 저장하기 전까지 id값이 없다. -> id 값이 없다는 것은 완전히 새로 생성하는 객체
        if (item.getId() == null) {
            em.persist(em); // 신규로 등록
        } else em.merge(item); // 나중에 설명함. (이미 db에 등록된것을 가지고 온것. update라 생각하면됨)
    }

    // item 하나 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // item list 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
