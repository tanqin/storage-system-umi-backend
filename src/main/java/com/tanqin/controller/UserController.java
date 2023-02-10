package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.entity.User;
import com.tanqin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword())
                .list();
        if (list.size() > 0) {
            Map map = new HashMap();
            map.put("userInfo", list.get(0));
            return Result.success(map);
        }
        return Result.fail();
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
        return userService.removeById(id) ? Result.success() : Result.fail();
    }
}
