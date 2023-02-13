package com.tanqin.controller;

import com.tanqin.common.JwtUtil;
import com.tanqin.entity.Menu;
import com.tanqin.entity.Result;
import com.tanqin.entity.User;
import com.tanqin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tanqin
 * @since 2023-02-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取登录用户菜单列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        User user = JwtUtil.parseJWT();
        List<Menu> list = menuService.lambdaQuery().like(Menu::getRoleIds, user.getRoleId()).list();
        return Result.success(list);
    }
}
