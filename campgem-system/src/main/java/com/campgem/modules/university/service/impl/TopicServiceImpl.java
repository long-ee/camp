package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.dto.TopicQueryDto;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.mapper.TopicMapper;
import com.campgem.modules.university.service.ITopicService;
import com.campgem.modules.university.vo.TopicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
