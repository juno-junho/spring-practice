package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    // 다른 객체로 생성하면 혹시 내용물이 달라질 수도 있음.
    MemoryMemberRepository memberRepository;
    // 각 테스트를 실행하기 전에 같은 MemoryMemberRepository를 만들고 MemberService에 넣어 같은 MemoryMemberRepository가 사용될수 있또록
    // 이것을 DI라고 함.

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 각 테스트 끝날때 마다 db의 값을 다 날려준다.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Hello");

        // when
        // 검증 할 것
        Long saveId = memberService.join(member);

        // then
        // 검증하면 됨.
        Member findMember = memberService.findOne(saveId).get();
        // 이름으로 검증.
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        //when
        Member member2 = new Member();
        member2.setName("spring");

        // then
        memberService.join(member1);
        // 예외가 터지면 성공
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //메세지 검증
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123123");
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}