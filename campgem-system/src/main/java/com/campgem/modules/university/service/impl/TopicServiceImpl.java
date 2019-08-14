package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.TopicQueryDto;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.mapper.TopicMapper;
import com.campgem.modules.university.service.ITopicService;
import com.campgem.modules.university.vo.TopicVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    @Override
    public IPage<TopicVo> queryPageList(Page page, TopicQueryDto queryDto) {
        return null;
    }

    @Override
    public TopicVo queryDetails(String id) {
        return null;
    }
}
