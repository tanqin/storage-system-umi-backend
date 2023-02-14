package com.tanqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanqin.entity.Kind;
import com.tanqin.mapper.KindMapper;
import com.tanqin.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-14
 */
@Service
public class KindServiceImpl extends ServiceImpl<KindMapper, Kind> implements KindService {
    @Autowired
    private KindMapper kindMapper;

    @Override
    public IPage pageQuery(IPage<Kind> page, LambdaQueryWrapper<Kind> lambdaQueryWrapper) {
        return kindMapper.pageQuery(page, lambdaQueryWrapper);

    }
}
