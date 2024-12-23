package com.perperon.mall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class PerperonServiceWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test2() {
        int n = 10;
        int sum = IntStream.rangeClosed(1, n).sum();
        System.out.println("1+2+3+...+" + n + " = " + sum);
    }

}
