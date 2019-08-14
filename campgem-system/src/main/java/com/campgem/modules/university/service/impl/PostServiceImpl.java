package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.PostQueryDto;
import com.campgem.modules.university.entity.Post;
import com.campgem.modules.university.mapper.PostMapper;
import com.campgem.modules.university.service.IPostService;
import com.campgem.modules.university.vo.PostVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 版块信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Override
    public IPage<PostVo> queryPageList(Page page, PostQueryDto queryDto) {
        return null;
    }

    @Override
    public PostVo queryDetails(String id) {
        return null;
    }
}
