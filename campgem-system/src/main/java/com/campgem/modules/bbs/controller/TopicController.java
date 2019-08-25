package com.campgem.modules.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.bbs.dto.*;
import com.campgem.modules.bbs.entity.Topic;
import com.campgem.modules.bbs.service.IReplyService;
import com.campgem.modules.bbs.service.ITopicService;
import com.campgem.modules.bbs.vo.ReplyVo;
import com.campgem.modules.bbs.vo.TopicVo;
import com.campgem.modules.common.service.ISysAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 话题信息管理接口
 * @Author: campgem
 */
@Slf4j
@Api(tags="话题信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class TopicController {
	@Resource
	private ITopicService topicService;
	@Resource
	private IReplyService replyService;
	@Resource
	private ISysAnnouncementService sysAnnouncementService;

	@ApiOperation(value="话题信息管理-热门话题列表查询", notes="F1 热门话题列表查询")
	@GetMapping(value = "/post/topic/hot/list")
	public Result<List<Topic>> queryHotTopicList() {
		LambdaQueryWrapper<Topic> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Topic::getDelFlag, 0);
		queryWrapper.orderByDesc(Topic::getReplyCount);
		Page<Topic> page = new Page<Topic>(1, 10);
		IPage<Topic> pageList = topicService.page(page, queryWrapper);
		return new Result<List<Topic>>().result(pageList.getRecords());
	}

	@ApiOperation(value="话题信息管理-话题列表分页查询", notes="F11 G161 话题列表分页查询")
	@GetMapping(value = "/post/topic/list")
	public Result<IPage<TopicVo>> queryTopicList(TopicQueryDto queryDto,
											  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											  HttpServletRequest req) {
		Page<TopicQueryDto> page = new Page<TopicQueryDto>(pageNo, pageSize);
		IPage<TopicVo> topicVos = topicService.queryPageList(page, queryDto);
		return new Result<IPage<TopicVo>>().result(topicVos);
	}

	@ApiOperation(value="话题信息管理-话题详情查询", notes="F12 话题详情查询（话题基本信息）")
	@GetMapping(value = "/post/topic/details")
	public Result<TopicVo> queryDetails(String topicId) {
		TopicVo topic = topicService.queryDetails(topicId);
		// 更新浏览数量
		topicService.updateTopicReplyVCount(topicId);
		return new Result<TopicVo>().result(topic);
	}

	@ApiOperation(value="话题信息管理-创建话题", notes="G162 创建/编辑话题-创建话题")
	@PostMapping(value = "/post/topic/create")
	public Result create(@Valid TopicDto topicDto) {
		String memberId = SecurityUtils.getCurrentUserMemberId();
		topicDto.setPublisherId(memberId);
		topicService.create(topicDto);
		return Result.ok();
	}

	@ApiOperation(value="话题信息管理-编辑话题", notes="G162 创建/编辑话题-编辑话题")
	@PostMapping(value = "/post/topic/edit")
	public Result edit(@Valid TopicDto topicDto) {
		topicService.create(topicDto);
		return Result.ok();
	}

	@ApiOperation(value="话题信息管理-话题回复列表查询", notes="F12 话题回复列表查询（回复基本信息）")
	@GetMapping(value = "/post/topic/reply/list")
	public Result<IPage<ReplyVo>> queryTopicReplyList(String topicId,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		Page<String> page = new Page<String>(pageNo, pageSize);
		IPage<ReplyVo> replyVos = replyService.queryPageList(page, topicId);
		return new Result<IPage<ReplyVo>>().result(replyVos);
	}


	@ApiOperation(value="话题信息管理-话题回复", notes="F12 话题回复")
	@PostMapping(value = "/post/topic/reply")
	public Result topicReply(@Valid TopicReplyDto topicReplyDto) {
		replyService.reply(topicReplyDto);
		return Result.ok();
	}

	@ApiOperation(value="话题信息管理-删除回复", notes="G162 创建/编辑话题-删除回复")
	@PostMapping(value = "/post/topic/reply/remove")
	public Result removeReply(String replyId) {
		replyService.removeReply(replyId);
		return Result.ok();
	}


	@ApiOperation(value="话题信息管理-话题回复点踩/点赞", notes="F12 0 点踩 1 点赞")
	@PostMapping(value = "/post/topic/reply/stand")
	public Result topicReplyStand(@Valid TopicReplyStandDto replyStandDto) {
		replyService.replyStand(replyStandDto);
		return Result.ok();
	}

	@ApiOperation(value="话题信息管理-发送站内信", notes="F14 发送站内信")
	@PostMapping(value = "/post/topic/letter/send")
	public Result sendLetter(@Valid TopicLetterDto letterDto) {
		sysAnnouncementService.sendTopicLetter(letterDto);
		return Result.ok();
	}

}
