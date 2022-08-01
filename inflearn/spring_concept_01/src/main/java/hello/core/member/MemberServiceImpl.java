package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // MemberServiceImpl는 추상화에도 구체화에도 의존하고있다 (DIP 위반)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
