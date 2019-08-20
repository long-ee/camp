package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.dto.TopicReplyDto;
import com.campgem.modules.university.dto.TopicReplyStandDto;
import com.campgem.modules.university.entity.Reply;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.entity.enums.ReplyStandTypeEnum;
import com.campgem.modules.university.mapper.ReplyMapper;
import com.campgem.modules.university.service.IReplyService;
import com.campgem.modules.university.service.ITopicService;
import com.campgem.modules.university.vo.ReplyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 话题回复信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private ITopicService topicService;

    @Override
    public IPage<ReplyVo> queryPageList(Page page, String topicId) {
        if(StringUtils.isBlank(topicId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        return replyMapper.queryPageList(page, topicId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void replyStand(TopicReplyStandDto standDto) {
        standDto.paramValidation();
        Reply reply = this.getById(standDto.getReplyId());
        if(null == reply){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        int dislikeCount = reply.getDislikeCount();
        int likeCount = reply.getLikeCount();
        // 点踩
        if(StringUtils.equals(ReplyStandTypeEnum.DISLIKE.code(), standDto.getStandValue())){
            boolean dislikeCountUpdate = new LambdaUpdateChainWrapper<Reply>(replyMapper)
                    .eq(Reply::getId, standDto.getReplyId()).set(Reply::getDislikeCount, (dislikeCount + 1) ).update();
            if(!dislikeCountUpdate){
                throw new JeecgBootException(StatusEnum.InternalError);
            }
            return;
        }
        // 点赞
        if(StringUtils.equals(ReplyStandTypeEnum.DISLIKE.code(), standDto.getStandValue())){
            boolean likeCountUpdate = new LambdaUpdateChainWrapper<Reply>(replyMapper)
                    .eq(Reply::getId, standDto.getReplyId()).set(Reply::getLikeCount, (likeCount + 1) ).update();
            if(!likeCountUpdate){
                throw new JeecgBootException(StatusEnum.InternalError);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reply(TopicReplyDto topicReplyDto) {
        topicReplyDto.paramValidation();
        Topic topic = topicService.getById(topicReplyDto.getTopicId());
        if(null == topic){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }

        LambdaQueryWrapper<Reply> replyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        replyLambdaQueryWrapper.eq(Reply::getTopicId, topicReplyDto.getTopicId());
        replyLambdaQueryWrapper.eq(Reply::getDelFlag, 0);
        int replyCount = this.count(replyLambdaQueryWrapper);

        Reply reply = new Reply();
        reply.setPostId(topic.getPostId());
        reply.setTopicId(topic.getId());
        reply.setLikeCount(0);
        reply.setDislikeCount(0);
        reply.setDelFlag(0);
        reply.setReplierId(topicReplyDto.getReplierId());
        reply.setReplyTime(new Date());
        reply.setReplyContent(topicReplyDto.getReplyCount());
        reply.setFloorNumber(replyCount + 2);
        this.save(reply);
        // 更新回复数量
        topicService.updateTopicReplyPCount(topicReplyDto);
    }
}
