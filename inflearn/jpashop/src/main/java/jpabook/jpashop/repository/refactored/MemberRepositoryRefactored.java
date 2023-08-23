package jpabook.jpashop.repository.refactored;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MemberRepositoryRefactored extends JpaRepository<Member, Long> {

    @Modifying()
    List<Member> findByName(String name);
}
