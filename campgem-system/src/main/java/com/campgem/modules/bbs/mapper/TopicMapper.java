package com.campgem.modules.bbs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.bbs.dto.TopicQueryDto;
import com.campgem.modules.bbs.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.bbs.vo.TopicVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface TopicMapper extends BaseMapper<Topic> {

    IPage<TopicVo> queryPageList(Page page, @Param("queryDto") TopicQueryDto queryDto);

    TopicVo queryDetails(@Param("topicId")String topicId);
}
