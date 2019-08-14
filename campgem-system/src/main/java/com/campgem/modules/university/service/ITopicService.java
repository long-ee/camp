package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.TopicQueryDto;
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
     * @param queryDto
     * @return
     */
    IPage<TopicVo> queryPageList(Page page, TopicQueryDto queryDto);

    /**
     * @param id
     * @return
     */
    TopicVo queryDetails(String id);
}
