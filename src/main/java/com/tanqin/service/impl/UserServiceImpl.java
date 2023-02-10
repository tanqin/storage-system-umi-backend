package com.tanqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanqin.entity.User;
import com.tanqin.mapper.UserMapper;
import com.tanqin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage pageQuery(IPage<User> page, LambdaQueryWrapper<User> lambdaQueryWrapper) {
        return userMapper.pageQuery(page, lambdaQueryWrapper);
    }
}
