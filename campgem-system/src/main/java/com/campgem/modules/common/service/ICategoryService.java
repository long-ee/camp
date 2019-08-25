package com.campgem.modules.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.common.dto.CategoryQueryDto;
import com.campgem.modules.common.entity.Category;
import com.campgem.modules.common.vo.CategoryVo;

import java.util.List;

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
    IPage<Category> queryPageList(Page page, CategoryQueryDto queryDto);

    /**
     * 查询城市信息详情
     * @param categoryId
     * @return
     */
    CategoryVo queryDetails(String categoryId);
    
    /**
     * 类型查询分类列表
     * @param type
     * @return
     */
    List<CategoryVo> queryByType(String type);
}
