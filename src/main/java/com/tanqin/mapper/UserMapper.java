package com.tanqin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tanqin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage pageQuery(IPage<User> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<User> lambdaQueryWrapper);
}
