package com.tanqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanqin.entity.Storage;
import com.tanqin.mapper.StorageMapper;
import com.tanqin.service.StorageService;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public IPage pageQuery(IPage<Storage> page, LambdaQueryWrapper<Storage> lambdaQueryWrapper) {
        return storageMapper.pageQuery(page, lambdaQueryWrapper);
    }
}
