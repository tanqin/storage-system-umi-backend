package com.tanqin.service.impl;

import com.tanqin.entity.User;
import com.tanqin.mapper.UserMapper;
import com.tanqin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
