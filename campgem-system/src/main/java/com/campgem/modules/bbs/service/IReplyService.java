package com.campgem.modules.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.bbs.dto.TopicReplyDto;
import com.campgem.modules.bbs.dto.TopicReplyStandDto;
import com.campgem.modules.bbs.entity.Reply;
import com.campgem.modules.bbs.vo.ReplyVo;

import java.util.List;

/**
 * @Description: 话题回复信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IReplyService extends IService<Reply> {

    List<ReplyVo> queryList(String topicId);

    /**
     * 分页查询话题回复列表
     * @param page
     * @param topicId
     * @return
     */
    IPage<ReplyVo> queryPageList(Page page, String topicId);

    /**
     * 回复点踩、点赞
     * @param standDto
     */
    void replyStand(TopicReplyStandDto standDto);

    /**
     * 话题回复
     * @param topicReplyDto
     */
    void reply(TopicReplyDto topicReplyDto);

    /**
     * 删除回复
     * @param replyId
     */
    void removeReply(String replyId);

}
