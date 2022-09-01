package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllbeanTest {
    // 실무에서 사용하는 예제
    @Test
    void findAllBean(){
        // Bean 등록 AutoAppConfig의 componentScan 통해서 DiscountPolicy 등록된 빈들이 map과 list에 들어감.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        // 1000원 할인
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        // discountService 제대로 받아졌는지 확인.
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        // 10 % 할인
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }
    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap; // bean 이름 : spring bean
        private final List<DiscountPolicy> policies; // 테스트 용 (이렇게 받을 수도 있다) // DiscountPolicy 다 가지고옴

        @Autowired //orderService를 건들면 너무 복잡해져서 하나 만듦.
        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
