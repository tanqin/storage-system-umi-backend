package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.entity.Kind;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.service.KindService;
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
@RequestMapping("/kind")
public class KindController {
    @Autowired
    private KindService kindService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        Integer pageNum = queryPageParams.getPageNum();
        Integer pageSize = queryPageParams.getPageSize();
        IPage<Kind> page = new Page(pageNum, pageSize);

        Map params = queryPageParams.getParams();
        String queryString = (String) params.get("queryString");
        LambdaQueryWrapper<Kind> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)) {
            lambdaQueryWrapper.like(Kind::getName, queryString).or().like(Kind::getRemark, queryString);
        }

        IPage result = kindService.pageQuery(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Kind kind) {
        return kindService.saveOrUpdate(kind) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return kindService.removeById(id) ? Result.success() : Result.fail();
    }
}
