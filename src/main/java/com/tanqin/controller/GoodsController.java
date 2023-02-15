package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.entity.Goods;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tanqin
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        Integer pageNum = queryPageParams.getPageNum();
        Integer pageSize = queryPageParams.getPageSize();
        IPage<Goods> page = new Page(pageNum, pageSize);

        Map params = queryPageParams.getParams();
        String queryString = (String) params.get("queryString");
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)) {
            lambdaQueryWrapper.like(Goods::getName, queryString).or().like(Goods::getRemark, queryString);
        }

        IPage result = goodsService.pageQuery(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Goods goods) {
        return goodsService.saveOrUpdate(goods) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return goodsService.removeById(id) ? Result.success() : Result.fail();
    }
}
