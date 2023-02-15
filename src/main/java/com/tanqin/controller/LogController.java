package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.entity.Log;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tanqin
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        Integer pageNum = queryPageParams.getPageNum();
        Integer pageSize = queryPageParams.getPageSize();
        IPage<Log> page = new Page(pageNum, pageSize);

        Map params = queryPageParams.getParams();
        String queryString = (String) params.get("queryString");
        LambdaQueryWrapper<Log> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)) {
            lambdaQueryWrapper.like(Log::getRemark, queryString);
        }

        IPage result = logService.pageQuery(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Log log) {
        return logService.saveOrUpdate(log) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return logService.removeById(id) ? Result.success() : Result.fail();
    }
}
