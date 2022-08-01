package hello.hellospring.repository;

import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaMemberRepository<Member, Long>, MemberRepository {

}
