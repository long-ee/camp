package com.campgem.modules.bbs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.bbs.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.bbs.vo.ReplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 话题回复信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ReplyMapper extends BaseMapper<Reply> {

    /**
     * 查询话题回复列表
     * @param page
     * @param topicId
     * @return
     */
    IPage<ReplyVo> queryPageList(Page page, @Param("topicId") String topicId);


    List<ReplyVo> queryList(@Param("topicId") String topicId);
}
