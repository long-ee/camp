package com.campgem.modules.bbs.service.impl;

import com.campgem.modules.bbs.entity.PostModerator;
import com.campgem.modules.bbs.mapper.PostModeratorMapper;
import com.campgem.modules.bbs.service.IPostModeratorService;
import com.campgem.modules.bbs.vo.PostModeratorVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 版块管理员关系信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class PostModeratorServiceImpl extends ServiceImpl<PostModeratorMapper, PostModerator> implements IPostModeratorService {

    @Resource
    private PostModeratorMapper postModeratorMapper;

    @Override
    public List<PostModeratorVo> queryList(String postId) {
        return postModeratorMapper.queryList(postId);
    }
}
