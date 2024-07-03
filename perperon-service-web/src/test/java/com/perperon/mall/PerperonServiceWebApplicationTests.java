package com.perperon.mall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
class PerperonServiceWebApplicationTests {

    @Test
   public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        boolean matches = encoder.matches("123456", "$2a$10$RNQFzPCWI8M1OwD7S/bpPuSl1v/kbWmFK4tmwLq1LPkGFvv9qzffO");
        System.out.println(matches);

    }

    @Test
    public void test2() {
        int n = 10;
        int sum = IntStream.rangeClosed(1, n).sum();
        System.out.println("1+2+3+...+" + n + " = " + sum);
    }

}
