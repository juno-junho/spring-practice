package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(value = false)
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("haha");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test()
    void 중복_회원_조회() throws Exception {
        //given
        Member member1 = new Member();
        String name = "kim1";
        member1.setName(name);

        Member member2 = new Member();
        member2.setName(name);

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);    // 예외가 발생해야 한다.
//        } catch (IllegalStateException e) {
//            return;
//        }
//        //then
//        Assertions.fail("예외가 발생해야 한다."); //코드가 여기 오면 안된다. (예외 발생하지 않으면 test fail 되게)

     // thenf
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }

}