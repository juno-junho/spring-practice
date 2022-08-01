package hello.core.member;

public interface MemberRepository {

    // 회원 저장
    void save(Member member);

    //회원 검색
    Member findById(Long memberId);
}