package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     - 기존에는 OrderServiceImpl 이 DiscountPolicy 인터페이스 뿐만아니라
     - fixDiscountPolicy 구현체도 함꼐 의존하고 있다, 이럴경우 DIP 원칙칙위반이다
     - 그래서 fixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 코드도 함께 변경해야 한다
     - 이 또한 OCP 원칙 위반이다, 따라서 인터페이스에만 의존하도록 바꾸어야 한다
     - 구현체가 없는데 어떻게 코드를 실행할 수 있을까?
     - 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    - 설계 변경으로 OrderServiceImpl은 DiscountPolicy 인터페이스만 의존한다
    - OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다
    - OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정한다
    - OrderServiceImpl은 이제부터 실행에만 집중하면 된다
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
