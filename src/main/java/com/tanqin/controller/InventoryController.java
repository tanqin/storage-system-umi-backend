package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.common.JwtUtil;
import com.tanqin.entity.Inventory;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.entity.User;
import com.tanqin.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tanqin
 * @since 2023-02-18
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * 仓库分页查询
     *
     * @param queryPageParams 查询参数
     * @return 分页数据
     */
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        // token 中获取用户信息
        User user = JwtUtil.parseJWT();
        Byte roleId = user.getRoleId();

        // 超级管理员才拥有查询权限
        if (roleId == 0) {
            // 获取页码、页容量
            Integer pageNum = queryPageParams.getPageNum();
            Integer pageSize = queryPageParams.getPageSize();
            // 构建分页器
            IPage<Inventory> page = new Page<>(pageNum, pageSize);

            // 获取请求参数
            Map params = queryPageParams.getParams();
            String queryString = (String) params.get("queryString");
            // 定义条件构造器
            LambdaQueryWrapper<Inventory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(queryString)) {
                // 添加请求条件
                lambdaQueryWrapper.like(Inventory::getItemNumber, queryString)
                        .or().like(Inventory::getItemName, queryString)
                        .or().like(Inventory::getLocation, queryString);
            }
            if (params.get("status") != null) {
                lambdaQueryWrapper.and(wrapper -> wrapper.eq(Inventory::getStatus, params.get("status")));
            }

            IPage result = inventoryService.pageQuery(page, lambdaQueryWrapper);
            return Result.success(result.getRecords(), result.getTotal());
        } else {
            // 403 Forbidden 响应： 当用户被认证后，但用户没有被授权在特定资源上执行操作。
            return Result.fail("无权操作！", HttpStatus.FORBIDDEN.value());
        }
    }

    /**
     * 新增或更新仓库
     *
     * @param inventory
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Inventory inventory) {
        return inventoryService.saveOrUpdate(inventory) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            return inventoryService.removeById(id) ? Result.success("删除成功！") : Result.fail("删除失败！");
        } catch (Exception e) {
            RuntimeException runtimeException = new RuntimeException(e);
            System.out.println(runtimeException);
            return Result.fail("删除失败！", HttpStatus.CONFLICT.value());
        }
    }
}
