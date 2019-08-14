package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.CategoryQueryDto;
import com.campgem.modules.university.entity.Category;
import com.campgem.modules.university.vo.CategoryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 分类信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 根据条件分页查询分类分页列表
     * @param queryDto
     * @return
     */
    IPage<CategoryVo> queryPageList(Page page, CategoryQueryDto queryDto);

    /**
     * 查询城市信息详情
     * @param categoryId
     * @return
     */
    CategoryVo queryDetails(String categoryId);
}
