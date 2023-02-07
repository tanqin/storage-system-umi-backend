package com.tanqin.mapper;

import com.tanqin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
