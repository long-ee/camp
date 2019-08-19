package com.campgem.modules.university.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.university.dto.PostQueryDto;
import com.campgem.modules.university.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.university.vo.PostVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 版块信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 根据条件查询版块基础信息
     * @param page
     * @param queryDto
     * @return
     */
    IPage<PostVo> queryPageList(Page page, @Param("queryDto") PostQueryDto queryDto);


    List<PostVo> queryList(@Param("queryDto") PostQueryDto queryDto);


    PostVo queryDetails(@Param("postId") String postId);
}
