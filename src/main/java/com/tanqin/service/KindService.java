package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.Kind;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-14
 */
public interface KindService extends IService<Kind> {

    IPage pageQuery(IPage<Kind> page, LambdaQueryWrapper<Kind> lambdaQueryWrapper);
}
