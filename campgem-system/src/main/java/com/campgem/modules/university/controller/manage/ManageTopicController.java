package com.campgem.modules.university.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.CityQueryDto;
import com.campgem.modules.university.dto.TopicQueryDto;
import com.campgem.modules.university.entity.Topic;
import com.campgem.modules.university.service.IReplyService;
import com.campgem.modules.university.service.ITopicService;
import com.campgem.modules.university.vo.TopicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 话题信息管理接口
 * @Author: campgemTony.X
 */
@Slf4j
@Api(tags="【管理端】话题信息管理接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageTopicController {
	@Resource
	private ITopicService topicService;
	@Resource
	private IReplyService replyService;

	/**
	 * 根据关键字查询话题列表信息
	 * @return
	 */
	@ApiOperation(value="Search topic list by keywords", notes="根据关键字查询话题列表信息")
	@GetMapping(value = "/topic/list")
	public Result<List<TopicVo>> searchTopicList(TopicQueryDto queryDto, HttpServletRequest req) {
		Result<List<TopicVo>> result = new Result<>();
		Page<CityQueryDto> page = new Page<CityQueryDto>(1, 10);
		IPage<TopicVo> pageList = topicService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList.getRecords());
		return result;
	}
	
	/**
	  * 分页列表查询
	 * @param queryDto
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="话题信息-分页列表查询", notes="话题信息-分页列表查询")
	@GetMapping(value = "/topic/pageList")
	public Result<IPage<TopicVo>> queryPageList(TopicQueryDto queryDto,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												HttpServletRequest req) {
		Result<IPage<TopicVo>> result = new Result<>();
		Page<TopicQueryDto> page = new Page<TopicQueryDto>(pageNo, pageSize);
		IPage<TopicVo> pageList = topicService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param topic
	 * @return
	 */
	@ApiOperation(value="话题信息-添加", notes="话题信息-添加")
	@PostMapping(value = "/topic/add")
	public Result<Topic> add(@RequestBody Topic topic) {
		Result<Topic> result = new Result<Topic>();
		try {
			topicService.save(topic);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param topic
	 * @return
	 */
	@ApiOperation(value="话题信息-编辑", notes="话题信息-编辑")
	@PostMapping(value = "/topic/edit")
	public Result<Topic> edit(@RequestBody Topic topic) {
		Result<Topic> result = new Result<Topic>();
		Topic topicEntity = topicService.getById(topic.getId());
		if(topicEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = topicService.updateById(topic);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@ApiOperation(value="话题信息-通过id删除", notes="话题信息-通过id删除")
	@PostMapping(value = "/topic/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			topicService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	 /**
	  * 通过id删除
	  * @param id
	  * @return
	  */
	 @ApiOperation(value="话题回复信息-通过id删除", notes="话题回复信息-通过id删除")
	 @PostMapping(value = "/topic/reply/delete")
	 public Result<?> deleteReply(@RequestParam(name="id",required=true) String id) {
		 try {
			 replyService.removeById(id);
		 } catch (Exception e) {
			 log.error("删除失败",e.getMessage());
			 return Result.error("删除失败!");
		 }
		 return Result.ok("删除成功!");
	 }


	 /**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value="话题信息-详情查询", notes="话题信息-详情查询")
	@GetMapping(value = "/topic/details")
	public Result<TopicVo> queryDetails(@RequestParam(name="id",required=true) String id) {
		Result<TopicVo> result = new Result<TopicVo>();
		TopicVo topic = topicService.queryDetails(id);
		if(topic==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(topic);
			result.setSuccess(true);
		}
		return result;
	}
}
