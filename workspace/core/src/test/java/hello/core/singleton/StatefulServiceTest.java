package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        /*
        - 사용자 A의 주문금액은 10000원이 되어야 하는데, 20000원 이라는 결과가 나왔다
        - 이는 StatefulService의 price필드가 옹유되는 필드라서 그렇다
        - 스프링 빈은 항상 무상태(stateless)로 설계해야 한다
         */

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자 A 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadA: 사용자 B 10000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();

        // ThreadA: 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
