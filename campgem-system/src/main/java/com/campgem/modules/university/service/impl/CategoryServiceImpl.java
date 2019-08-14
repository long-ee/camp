package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.CategoryQueryDto;
import com.campgem.modules.university.entity.Category;
import com.campgem.modules.university.mapper.CategoryMapper;
import com.campgem.modules.university.service.ICategoryService;
import com.campgem.modules.university.vo.CategoryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 分类信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public IPage<CategoryVo> queryPageList(Page page, CategoryQueryDto queryDto) {
        return null;
    }

    @Override
    public CategoryVo queryDetails(String categoryId) {
        return null;
    }
}
