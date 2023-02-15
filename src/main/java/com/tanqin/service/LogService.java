package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.Log;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-15
 */
public interface LogService extends IService<Log> {

    IPage pageQuery(IPage<Log> page, LambdaQueryWrapper<Log> lambdaQueryWrapper);
}
