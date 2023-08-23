package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //jpa가 제공하는 표준 어노테이션. 스프링이 entityManager만들어서 주입해줌
//    @PersistenceContext
//    private EntityManager em;
    private final EntityManager em;

    // member 저장 (jpa가 저장)
    public void save(Member member) {
        em.persist(member);
    }

    // Member 조회 (id값으로 member 조회)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 리스트 조회 (jpql을 작성해야함.) jpql + 반환타입
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 검색
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name) // 위 jpql name에 binding
                .getResultList();
    }
}
