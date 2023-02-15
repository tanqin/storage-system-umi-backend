package com.tanqin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tanqin.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tanqin
 * @since 2023-02-14
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage pageQuery(IPage<Goods> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<Goods> lambdaQueryWrapper);
}
