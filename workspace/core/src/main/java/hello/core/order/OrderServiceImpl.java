package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
     - 기존에는 OrderServiceImpl 이 DiscountPolicy 인터페이스 뿐만아니라
     - fixDiscountPolicy 구현체도 함꼐 의존하고 있다, 이럴경우 DIP 원칙칙위반이다
     - 그래서 fixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 코드도 함께 변경해야 한다
     - 이 또한 OCP 원칙 위반이다, 따라서 인터페이스에만 의존하도록 바꾸어야 한다
     - 구현체가 없는데 어떻게 코드를 실행할 수 있을까?
     - 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다
     */

    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
