package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.DateUtils;
import com.campgem.modules.university.dto.PostQueryDto;
import com.campgem.modules.university.entity.Post;
import com.campgem.modules.university.entity.PostModerator;
import com.campgem.modules.university.entity.Reply;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.mapper.PostMapper;
import com.campgem.modules.university.service.IPostModeratorService;
import com.campgem.modules.university.service.IPostService;
import com.campgem.modules.university.service.IReplyService;
import com.campgem.modules.university.service.ITopicService;
import com.campgem.modules.university.vo.PostModeratorVo;
import com.campgem.modules.university.vo.PostVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Description: 版块信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
    @Resource
    private PostMapper postMapper;
    @Resource
    private ITopicService topicService;
    @Resource
    private IReplyService replyService;
    @Resource
    private IPostModeratorService postModeratorService;

    @Override
    public IPage<PostVo> queryPageList(Page page, PostQueryDto queryDto) {
        IPage<PostVo> postVos = postMapper.queryPageList(page, queryDto);
        if(postVos.getTotal() > 0){
            List<PostVo> postList = new ArrayList<>();
            List<PostVo> postVoList = postVos.getRecords();
            postVoList.forEach(postVo -> {
                this.convert(postVo);
                postList.add(postVo);
            });
            postVos.setRecords(postList);
        }
        return postVos;
    }

    /**
     * 查询冗余信息
     * @param postVo
     */
    private void convert(PostVo postVo){
        String postId = postVo.getId();
        // 查询今日话题数
        LambdaQueryWrapper<Topic> todayTopicQuery = new LambdaQueryWrapper<>();
        todayTopicQuery.eq(Topic::getPostId, postId);
        todayTopicQuery.between(Topic::getCreateTime, DateUtils.getStartTime(Calendar.getInstance()), DateUtils.getEndTime(Calendar.getInstance()));
        todayTopicQuery.eq(Topic::getDelFlag, 0);
        todayTopicQuery.orderByDesc(Topic::getCreateTime);
        List<Topic> todayTopics = topicService.list(todayTopicQuery);
        int todayTopicCount = 0;
        if(CollectionUtils.isNotEmpty(todayTopics)){
            todayTopicCount = todayTopics.size();
            postVo.setLatestTopic(todayTopics.get(0));
        }
        // 查询话题总数
        LambdaQueryWrapper<Topic> topicQuery = new LambdaQueryWrapper<>();
        topicQuery.eq(Topic::getPostId, postId);
        topicQuery.eq(Topic::getDelFlag, 0);
        int topicCount = topicService.count(topicQuery);
        // 查询版块话题总回复数
        LambdaQueryWrapper<Reply> replyQuery = new LambdaQueryWrapper<>();
        replyQuery.eq(Reply::getPostId, postId);
        replyQuery.eq(Reply::getDelFlag, 0);
        int replyCount = replyService.count(replyQuery);
        // 查询管理员信息
        LambdaQueryWrapper<PostModerator> postModeratorQuery = new LambdaQueryWrapper<>();
        postModeratorQuery.eq(PostModerator::getPostId, postId);
        postModeratorQuery.eq(PostModerator::getIsPrimaryAdmin, 1);
        List<PostModeratorVo> postModerators = postModeratorService.queryList(postId);
        if(CollectionUtils.isNotEmpty(postModerators)){
            postVo.setModerators(postModerators);
            postVo.setPrimaryModerator(postModerators.get(0));
        }
        postVo.setTodayTopicCount(todayTopicCount);
        postVo.setTopicCount(topicCount);
        postVo.setReplyCount(replyCount);
    }

    @Override
    public PostVo queryDetails(String id) {
        if(StringUtils.isBlank(id)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        PostVo postVo = postMapper.queryDetails(id);
        if(null == postVo){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        this.convert(postVo);
        return postVo;
    }
}
