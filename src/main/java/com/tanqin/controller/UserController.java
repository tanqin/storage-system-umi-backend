package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.common.JwtUtil;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.entity.User;
import com.tanqin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private HttpServletRequest request;

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        try {
            List<User> list = userService.lambdaQuery()
                    .eq(User::getUsername, user.getUsername())
                    .eq(User::getPassword, user.getPassword())
                    .list();
            if (list.size() > 0) {
                String token = JwtUtil.sign(list.get(0));
                Map map = new HashMap();
                map.put("token", token);
                return Result.success(map);
            } else {
                return Result.fail("用户名或密码错误!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Integer statusCode = 500;
            response.setStatus(statusCode);
            return Result.fail(e.getMessage(), statusCode);
        }
    }

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return Result.fail("失败：用户名和密码不能为空！");
        }
        Long count = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        if (count > 0) {
            return Result.fail("失败：用户名已被占用，换一个试试！");
        }
        userService.save(user);
        return Result.success("注册成功！");
    }

    // 获取用户信息
    @GetMapping("/info")
    public Result info() {
        User baseUserInfo = JwtUtil.parseJWT();

        User user = userService.getById(baseUserInfo.getId());
        System.out.println(user);
        return user != null ? Result.success(user) : Result.fail();
    }

    // 分页查询
    @PostMapping("/pageList")
    public Result pageList(@RequestBody QueryPageParams queryPageParams) {
        Integer pageNum = queryPageParams.getPageNum();
        Integer pageSize = queryPageParams.getPageSize();
        Map params = queryPageParams.getParams();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.nested(StringUtils.isNotBlank((CharSequence) params.get("queryString")),
                wrapper -> wrapper.like(User::getUsername, params.get("queryString"))
                        .or().like(User::getNickname, params.get("queryString"))
                        .or().like(User::getPhone, params.get("queryString"))
        );
        if (params.get("sex") != null) {
            lambdaQueryWrapper.and(wrapper -> wrapper.eq(User::getSex, params.get("sex")));
        }
        if (params.get("roleId") != null) {
            lambdaQueryWrapper.and(wrapper -> wrapper.eq(User::getRoleId, params.get("roleId")));
        }

        String token = request.getHeader("Authorization");
        User user = JwtUtil.parseJWT(token);

        if (user.getId() == 1 && user.getRoleId() == 0) {
            // 超级管理员
            lambdaQueryWrapper.orderByAsc(User::getRoleId);
        } else if (user.getRoleId() == 1) {
            // 管理员
            lambdaQueryWrapper.and(wrapper -> wrapper.eq(User::getRoleId, 2));
        } else {
            return Result.fail();
        }


        IPage<User> page = new Page<>(pageNum, pageSize);

        IPage result = userService.pageQuery(page, lambdaQueryWrapper);

        return Result.success(result.getRecords(), result.getTotal());
    }

    // 更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody User user) {

        return userService.updateById(user) ? Result.success() : Result.fail();
    }

    // 删除用户
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        String token = request.getHeader("Authorization");
        User user = JwtUtil.parseJWT(token);
        if (id == 1 || user.getRoleId() == 2) {
            return Result.fail("操作失败，你没有该操作权限！");
        } else if (user.getRoleId() == 1) {
            // 不能删除与自己同级别的用户
            User willDeleteUser = userService.getById(id);
            Byte willDeleteUserRoleId = willDeleteUser.getRoleId();
            if (user.getRoleId() == willDeleteUserRoleId) {
                return Result.fail("操作失败，你没有该操作权限！");
            }
        }
        return userService.removeById(id) ? Result.success() : Result.fail();
    }
}
