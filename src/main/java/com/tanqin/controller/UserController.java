package com.tanqin.controller;

import com.tanqin.entity.User;
import com.tanqin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/login")
    public String login(User user) {
        List<User> list = userService.lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword())
                .list();
        if (list.size() > 0) {
            return "登录成功";
        }

        return "登录失败";
    }
}
