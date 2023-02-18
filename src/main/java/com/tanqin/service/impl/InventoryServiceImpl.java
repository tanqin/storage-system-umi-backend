package com.tanqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanqin.entity.Inventory;
import com.tanqin.mapper.InventoryMapper;
import com.tanqin.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-18
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public IPage pageQuery(IPage<Inventory> page, LambdaQueryWrapper<Inventory> lambdaQueryWrapper) {
        return inventoryMapper.pageQuery(page, lambdaQueryWrapper);

    }
}
