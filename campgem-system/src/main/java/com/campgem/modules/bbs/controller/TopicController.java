package com.campgem.modules.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.bbs.dto.TopicReplyStandDto;
import com.campgem.modules.bbs.dto.TopicQueryDto;
import com.campgem.modules.bbs.entity.Topic;
import com.campgem.modules.bbs.service.IReplyService;
import com.campgem.modules.bbs.service.ITopicService;
import com.campgem.modules.bbs.vo.ReplyVo;
import com.campgem.modules.bbs.vo.TopicVo;
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

	@ApiOperation(value="话题信息管理-热门话题列表查询", notes="话题信息管理-热门话题列表查询")
	@GetMapping(value = "/post/topic/hot/list")
	public Result<List<Topic>> queryHotTopicList() {
		LambdaQueryWrapper<Topic> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Topic::getDelFlag, 0);
		queryWrapper.orderByDesc(Topic::getReplyCount);
		Page<Topic> page = new Page<Topic>(1, 10);
		IPage<Topic> pageList = topicService.page(page, queryWrapper);
		return new Result<List<Topic>>().result(pageList.getRecords());
	}

	@ApiOperation(value="话题信息管理-话题列表分页查询", notes="话题信息管理-话题列表分页查询")
	@GetMapping(value = "/post/topic/list")
	public Result<IPage<TopicVo>> queryTopicList(TopicQueryDto queryDto,
											  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											  HttpServletRequest req) {
		Page<TopicQueryDto> page = new Page<TopicQueryDto>(pageNo, pageSize);
		IPage<TopicVo> topicVos = topicService.queryPageList(page, queryDto);
		return new Result<IPage<TopicVo>>().result(topicVos);
	}

	@ApiOperation(value="话题信息管理-话题详情查询", notes="话题信息管理-话题详情查询")
	@GetMapping(value = "/post/topic/details")
	public Result<TopicVo> queryDetails(String topicId) {
		TopicVo topic = topicService.queryDetails(topicId);
		return new Result<TopicVo>().result(topic);
	}


	@ApiOperation(value="话题信息管理-话题回复列表查询", notes="话题信息管理-话题回复列表查询")
	@GetMapping(value = "/post/topic/reply/list")
	public Result<IPage<ReplyVo>> queryTopicReplyList(String topicId,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		Page<String> page = new Page<String>(pageNo, pageSize);
		IPage<ReplyVo> replyVos = replyService.queryPageList(page, topicId);
		return new Result<IPage<ReplyVo>>().result(replyVos);
	}


	@ApiOperation(value="话题信息管理-话题回复", notes="话题信息管理-话题回复")
	@PostMapping(value = "/post/topic/reply")
	public Result topicReply(@Valid TopicReplyStandDto replyStandDto) {
		replyService.replyStand(replyStandDto);
		return Result.ok();
	}


	@ApiOperation(value="话题信息管理-话题回复点踩/点赞", notes="0 点踩 1 点赞")
	@PostMapping(value = "/post/topic/reply/stand")
	public Result topicReplyStand(@Valid TopicReplyStandDto replyStandDto) {
		replyService.replyStand(replyStandDto);
		return Result.ok();
	}

}
