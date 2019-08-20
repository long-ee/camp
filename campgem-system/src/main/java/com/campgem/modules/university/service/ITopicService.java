package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.TopicQueryDto;
import com.campgem.modules.university.dto.TopicReplyDto;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.vo.TopicVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ITopicService extends IService<Topic> {

    /**
     * 根据条件查询话题列表分页
     * @param page
     * @param queryDto
     * @return
     */
    IPage<TopicVo> queryPageList(Page page, TopicQueryDto queryDto);

    /**
     * 查询话题详情
     * @param id
     * @return
     */
    TopicVo queryDetails(String id);

    /**
     * 更新话题回复数量
     */
    void updateTopicReplyPCount(TopicReplyDto topicReplyDto);

    /**
     * 更新话题浏览数量
     */
    void updateTopicReplyVCount(String topicId);
}
