package com.campgem.modules.bbs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.bbs.dto.TopicDto;
import com.campgem.modules.bbs.dto.TopicQueryDto;
import com.campgem.modules.bbs.dto.TopicReplyDto;
import com.campgem.modules.bbs.entity.Topic;
import com.campgem.modules.bbs.mapper.TopicMapper;
import com.campgem.modules.bbs.service.ITopicService;
import com.campgem.modules.bbs.vo.TopicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {
    @Resource
    private TopicMapper topicMapper;

    @Override
    public IPage<TopicVo> queryPageList(Page page, TopicQueryDto queryDto) {
        return topicMapper.queryPageList(page, queryDto);
    }

    @Override
    public TopicVo queryDetails(String id) {
        if(StringUtils.isBlank(id)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        TopicVo topicVo = topicMapper.queryDetails(id);
        if(null == topicVo){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        return topicVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void updateTopicReplyPCount(TopicReplyDto topicReplyDto) {
        topicReplyDto.paramValidation();
        Topic topic = this.getById(topicReplyDto.getTopicId());
        if(null == topic){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        int replyCount = topic.getReplyCount();
        boolean replyCountUpdate = new LambdaUpdateChainWrapper<Topic>(topicMapper)
                .eq(Topic::getId, topic.getId())
                .set(Topic::getReplyCount, (replyCount + 1) )
                .set(Topic::getLastReplier, topicReplyDto.getReplierName())
                .set(Topic::getLastReplyTime, new Date()).update();
        if(!replyCountUpdate){
            throw new JeecgBootException(StatusEnum.InternalError);
        }
    }

    @Override
    public synchronized void updateTopicReplyVCount(String topicId) {
        if(StringUtils.isBlank(topicId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Topic topic = this.getById(topicId);
        if(null == topic){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        int viewCount = topic.getViewCount();
        boolean viewCountUpdate = new LambdaUpdateChainWrapper<Topic>(topicMapper)
                .eq(Topic::getId, topicId).set(Topic::getViewCount, (viewCount + 1) ).update();
        if(!viewCountUpdate){
            throw new JeecgBootException(StatusEnum.InternalError);
        }
    }

    @Override
    public void create(TopicDto topicDto) {
        if(StringUtils.isBlank(topicDto.getPublisherId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Topic topic = BeanConvertUtils.convertBean(topicDto, Topic.class);
        topic.setReplyCount(0);
        topic.setViewCount(0);
        this.save(topic);
    }

    @Override
    public void edit(TopicDto topicDto) {
        if(StringUtils.isBlank(topicDto.getId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Topic topic = this.getById(topicDto.getId());
        if(null == topic){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        topic = BeanConvertUtils.convertBean(topicDto, Topic.class);
        this.updateById(topic);
    }
}
