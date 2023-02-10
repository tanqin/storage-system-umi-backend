package com.tanqin;

import com.tanqin.common.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageSystemUmiBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void generateToken() {
        String username = "admin";
        String password = "admin";
        String token = JwtUtil.sign(username, password);
        System.out.println(token);
    }

    @Test
    void verifyToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImFkbWluIiwiZXhwIjoxNjc2MDY2ODk1LCJ1c2VybmFtZSI6ImFkbWluIn0.hgmJ3klLvfbqInRL6VilikgvGXixucffirzxnT_3pcY";
        Boolean b = JwtUtil.verify(token);
        System.out.println(b);
    }

}
