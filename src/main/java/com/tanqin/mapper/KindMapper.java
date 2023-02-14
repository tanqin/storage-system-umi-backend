package com.tanqin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tanqin.entity.Kind;
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
public interface KindMapper extends BaseMapper<Kind> {

    IPage pageQuery(IPage<Kind> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<Kind> lambdaQueryWrapper);
}
