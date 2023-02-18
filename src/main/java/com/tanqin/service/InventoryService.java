package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.Inventory;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-18
 */
public interface InventoryService extends IService<Inventory> {

    IPage pageQuery(IPage<Inventory> page, LambdaQueryWrapper<Inventory> lambdaQueryWrapper);
}
