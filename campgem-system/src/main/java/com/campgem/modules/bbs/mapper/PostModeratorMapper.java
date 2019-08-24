package com.campgem.modules.bbs.mapper;

import com.campgem.modules.bbs.entity.PostModerator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.bbs.vo.PostModeratorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 版块管理员关系信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface PostModeratorMapper extends BaseMapper<PostModerator> {
    /**
     * 根据版块ID查询版块管理员信息
     * @param postId
     * @return
     */
    List<PostModeratorVo> queryList(@Param("postId") String postId);
}
