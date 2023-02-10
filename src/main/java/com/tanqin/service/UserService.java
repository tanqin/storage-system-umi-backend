package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
public interface UserService extends IService<User> {
    IPage pageQuery(IPage<User> page, LambdaQueryWrapper<User> lambdaQueryWrapper);
}
