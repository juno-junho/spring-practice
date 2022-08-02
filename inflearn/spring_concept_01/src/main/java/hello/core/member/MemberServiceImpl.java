package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // MemberServiceImpl는 추상화에도 구체화에도 의존하고있다 (DIP 위반)
    private final MemberRepository memberRepository; /* = new MemoryMemberRepository();*/

    @Autowired  // ac.getBean(MemberRepository.class)처럼 동작한다고 생각하면 됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
