package com.campgem.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.common.dto.CategoryQueryDto;
import com.campgem.modules.common.entity.Category;
import com.campgem.modules.common.mapper.CategoryMapper;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.common.vo.CategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

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
    
    @Override
    public List<CategoryVo> queryByType(String type) {
        List<Category> data = baseMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getCategoryType, type));
        return BeanConvertUtils.copyList(data, CategoryVo.class);
    }
}
