package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 공연 기획자

    // 밖에서 생성해서 넣어준다. 생성자 주입
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
    
    // 이렇게 역할과 구현을 명확히 분리해서 역할에 구현이 들어가도록 함으로서 구성을 빠르게 파악 가능. & 중복 제거

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    // memberRepository 구현
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        // 이것만 바꿔서 갈아 끼울 수 있다.
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    // DiscountPolicy 구현
     public DiscountPolicy discountPolicy() {
       // 이것만 바꿔서 갈아 끼울 수 있다.
//        return new FixDiscountPolicy();
       return new RateDiscountPolicy();
    }
}
