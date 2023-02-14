package com.tanqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanqin.common.JwtUtil;
import com.tanqin.entity.QueryPageParams;
import com.tanqin.entity.Result;
import com.tanqin.entity.Storage;
import com.tanqin.entity.User;
import com.tanqin.service.StorageService;
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
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * @param queryPageParams 查询参数
     * @return 分页数据
     */
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParams queryPageParams) {
        User user = JwtUtil.parseJWT();
        Byte roleId = user.getRoleId();
        if (roleId == 0) {

            Integer pageNum = queryPageParams.getPageNum();
            Integer pageSize = queryPageParams.getPageSize();
            IPage<Storage> page = new Page<>(pageNum, pageSize);

            Map params = queryPageParams.getParams();
            String queryString = (String) params.get("queryString");
            LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(StringUtils.isNotBlank(queryString), Storage::getName, queryString)
                    .or().like(StringUtils.isNotBlank(queryString), Storage::getRemark, queryString);

            IPage result = storageService.pageQuery(page, lambdaQueryWrapper);
            return Result.success(result.getRecords(), result.getTotal());
        } else {
            return Result.fail();
        }
    }

    /**
     * 新增或更新仓库
     *
     * @param storage
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Storage storage) {
        return storageService.saveOrUpdate(storage) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return storageService.removeById(id) ? Result.success() : Result.fail();
    }
}
