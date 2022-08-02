package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액. 얼마 할인되었는지를 return
      */

    int discount(Member member, int price);
}
