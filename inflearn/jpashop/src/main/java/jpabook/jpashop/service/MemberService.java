package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 있는 필드만 생성자 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member) {
        /*
        여기서 중복 회원 검증 해야함.
         */
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
        //id값이라도 돌려줘야 뭐가 저장되었는지 알 수 있다.
    }

    private void validateDuplicateMember(Member member) {
        // Exception 문제생기면 예외
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    // 회원 한건만 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
        // 변경감지로 update
    }
}
