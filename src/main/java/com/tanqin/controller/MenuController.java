package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.common.JwtUtil;
import com.tanqin.entity.Menu;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.entity.User;
import com.tanqin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * @return
     * @desc 获取登录用户菜单列表
     */
    @GetMapping("/auth")
    public Result auth() {
        User user = JwtUtil.parseJWT();
        List<Menu> list = menuService.lambdaQuery().like(Menu::getRoleIds, user.getRoleId()).eq(Menu::getIsValid, true).orderByAsc(Menu::getLevel).list();
        return Result.success(list);
    }

    /**
     * @desc 菜单分页查询
     */
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        User user = JwtUtil.parseJWT();
        if (user.getRoleId() == 0) {
            Integer pageNum = queryPageParams.getPageNum();
            Integer pageSize = queryPageParams.getPageSize();
            Map params = queryPageParams.getParams();
            String queryString = (String) params.get("queryString");
            String roleIds = (String) params.get("roleIds");

            LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.nested(StringUtils.isNotBlank(queryString),
                    wrapper -> wrapper.like(Menu::getName, queryString)
                            .or().like(Menu::getPath, queryString)
                            .or().like(Menu::getComponent, queryString));
            lambdaQueryWrapper.and(StringUtils.isNotBlank(roleIds), wrapper -> wrapper.like(Menu::getRoleIds, roleIds));
            lambdaQueryWrapper.orderByAsc(Menu::getLevel);
            IPage<Menu> page = new Page<>(pageNum, pageSize);
            IPage result = menuService.pageQuery(page, lambdaQueryWrapper);
            return Result.success(result.getRecords(), result.getTotal());
        }
        return Result.fail();
    }

    /**
     * @param menu
     * @return
     * @desc 添加或修改菜单
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    /**
     * @param id
     * @return
     * @desc 根据 id 删除菜单项
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        User user = JwtUtil.parseJWT();
        if (user.getRoleId() == 0) {
            boolean flag = menuService.removeById(id);
            return flag ? Result.success() : Result.fail();
        }
        return Result.fail();
    }
}
