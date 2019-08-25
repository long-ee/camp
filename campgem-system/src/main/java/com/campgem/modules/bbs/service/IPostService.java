package com.campgem.modules.bbs.service;

import com.campgem.modules.bbs.dto.PostQueryDto;
import com.campgem.modules.bbs.entity.Post;
import com.campgem.modules.bbs.vo.PostVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 版块信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IPostService extends IService<Post> {
    /**
     * @param queryDto
     * @return
     */
    IPage<PostVo> queryPageList(Page page, PostQueryDto queryDto);

    /**
     * @param id
     * @return
     */
    PostVo queryDetails(String id);
    
}
