package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

@SpringBootTest
public class BeanTest {

    // 같은 타입(Food) 로 등록한 빈이 2개(Chicken, Pizza) 라서 자동 등록(@Autowired) 불가능
    // 1번째 방법 : 직접 명시
    @Autowired
    Food pizza;

    @Autowired
    Food chicken;
//   알 수 있는점 : @Autowired는 기본적으로는 타입으로 찾고 그 뒤에 이름으로 찾는다.



//   2번 째 방법: 주입하려는 클래스에서 @Primary를 붙여준다
//   같은 타입의 빈이 여러개 있어도 @Primary를 붙여준 클래스가 우선권을 가진다.
    @Autowired
    private Food food;


//  3번째 방법: 주입하고 싶은 Bean 에 @Qualifier("별명")을 붙여준다

    @Autowired
    @Qualifier("pizza")
    private Food food2;



    @Test
    @DisplayName("테스트1: 1번째 방법")
    void test1() {
        pizza.eat(); // ?? 왜 치킨을 먹는다가 나오지??? @Primary 때문인가??? -> 맞는듯
                    // 기본적인 우선 순위 는 @Primary 가 더 높지만 @Qualifier("별명")을 @autowired에 붙여주면@Qualifier("별명")을 찾음
                    // 따라서 범용적인 빈에는 @Primary를 붙여주고 지엽적인 빈에는 @Qualifier 를 붙여주는게 제일 좋음
        chicken.eat();
    }

    @Test
    @DisplayName("테스트2: 2번째 방법")
    void test2() {
        food.eat(); // 치킨을 먹습니다.
    }

    @Test
    @DisplayName("테스트3: 3번째 방법 @Qualifier")
    void test3() {
        food2.eat(); // 피자를 먹습니다.
    }
}
