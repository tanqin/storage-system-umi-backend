package com.tanqin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tanqin.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tanqin
 * @since 2023-02-15
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

    IPage pageQuery(IPage<Log> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<Log> lambdaQueryWrapper);
}
