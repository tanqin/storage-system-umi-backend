package com.tanqin;

import com.tanqin.common.JwtUtil;
import com.tanqin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageSystemUmiBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void generateToken() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        String token = JwtUtil.sign(user);
        System.out.println(token);
    }

    @Test
    void verifyToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImFkbWluIiwiZXhwIjoxNjc2MDY2ODk1LCJ1c2VybmFtZSI6ImFkbWluIn0.hgmJ3klLvfbqInRL6VilikgvGXixucffirzxnT_3pcY";
        Boolean b = JwtUtil.verify(token);
        System.out.println(b);
    }

}
