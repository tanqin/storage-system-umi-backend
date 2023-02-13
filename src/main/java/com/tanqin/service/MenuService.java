package com.tanqin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanqin.entity.Menu;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tanqin
 * @since 2023-02-12
 */
public interface MenuService extends IService<Menu> {

    IPage pageQuery(IPage<Menu> page, LambdaQueryWrapper<Menu> lambdaQueryWrapper);
}
