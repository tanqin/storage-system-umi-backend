package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.Storage;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-14
 */
public interface StorageService extends IService<Storage> {

    IPage pageQuery(IPage<Storage> page, LambdaQueryWrapper<Storage> lambdaQueryWrapper);
}
