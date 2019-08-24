package com.campgem.modules.bbs.service;

import com.campgem.modules.bbs.entity.PostModerator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.bbs.vo.PostModeratorVo;

import java.util.List;

/**
 * @Description: 版块管理员关系信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IPostModeratorService extends IService<PostModerator> {

    /**
     * 根据论坛版块ID查询版块管理员信息
     * @param postId
     * @return
     */
    List<PostModeratorVo> queryList(String postId);

}
