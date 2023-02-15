package com.tanqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanqin.entity.Log;
import com.tanqin.mapper.LogMapper;
import com.tanqin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-15
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public IPage pageQuery(IPage<Log> page, LambdaQueryWrapper<Log> lambdaQueryWrapper) {
        return logMapper.pageQuery(page, lambdaQueryWrapper);

    }
}
