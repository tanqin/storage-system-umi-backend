package com.tanqin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tanqin.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tanqin
 * @since 2023-02-18
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

    IPage pageQuery(IPage<Inventory> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<Inventory> lambdaQueryWrapper);
}
