package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.dto.RequirementReviewDto;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.entity.RequirementsReviews;
import com.campgem.modules.trade.entity.RequirementsReviewsShields;
import com.campgem.modules.trade.service.IRequirementsReviewsService;
import com.campgem.modules.trade.service.IRequirementsReviewsShieldsService;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.RequirementsDetailVo;
import com.campgem.modules.trade.vo.RequirementsReviewsVo;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.common.entity.enums.CategoryTypeEnum;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.user.service.IUserBaseService;
import com.campgem.modules.common.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 需求板块
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "需求信息管理接口")
public class RequirementsController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IRequirementsService requirementsService;
	@Resource
	private IRequirementsReviewsService requirementsReviewsService;
	@Resource
	private IRequirementsReviewsShieldsService requirementsReviewsShieldsService;
	@Resource
	private IUserBaseService userBaseService;
	
	@ApiOperation(value = "需求分类查询", notes = "C15 需求列表")
	@GetMapping(value = "/requirements/category")
	public Result<List<CategoryVo>> queryCategoryList() {
		Result<List<CategoryVo>> result = new Result<>();
		List<CategoryVo> data = categoryService.queryByType(CategoryTypeEnum.NEED.code());
		result.setResult(data);
		return result;
	}
	
	@ApiOperation(value = "需求分页查询", notes = "C1 商品列表")
	@GetMapping(value = "/requirements")
	public Result<IPage<RequirementsVo>> queryRequirementPageList(RequirementsQueryDto queryDto,
	                                                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<RequirementsVo>> result = new Result<>();
		Page<RequirementsQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<RequirementsVo> pageList = requirementsService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "需求详情", notes = "C151")
	@GetMapping("/requirements/{requirementId}/detail")
	@ApiImplicitParam(name = "requirementId", value = "需求ID", required = true, paramType = "path")
	public Result<RequirementsDetailVo> queryRequirementDetail(@PathVariable String requirementId) {
		RequirementsDetailVo detail = requirementsService.queryRequirementDetail(requirementId);
		return new Result<RequirementsDetailVo>().result(detail);
	}
	
	@ApiOperation(value = "需求留言分页查询", notes = "C151")
	@GetMapping("/requirements/{requirementId}/review")
	@ApiImplicitParam(name = "requirementId", value = "需求ID", required = true, paramType = "path")
	public Result<IPage<RequirementsReviewsVo>> queryRequirementReviewPageList(@PathVariable String requirementId,
	                                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<String> page = new Page<>(pageNo, pageSize);
		IPage<RequirementsReviewsVo> pageList = requirementsReviewsService.queryPageList(page, requirementId);
		return new Result<IPage<RequirementsReviewsVo>>().result(pageList);
	}
	
	@ApiOperation(value = "需求留言屏蔽接口", notes = "C151")
	@PostMapping(value = "/requirements/{requirementId}/shields")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "requirementId", value = "需求ID", required = true, paramType = "path"),
			@ApiImplicitParam(name = "targetId", value = "屏蔽的用户ID", required = true, paramType = "form")
	})
	public Result addUserReviewShield(@PathVariable String requirementId, String targetId) {
		if (StringUtils.isEmpty(targetId)) {
			throw new JeecgBootException("用户ID不能为空");
		}
		
		// 判断当前用户是需求的发布者
		Requirements requirements = requirementsService.getById(requirementId);
		if (requirements == null) {
			throw new JeecgBootException("需求不存在");
		}
		
		if (!requirements.getUid().equals(SecurityUtils.getCurrentUserUid())) {
			throw new JeecgBootException("不是需求的发布者，不能屏蔽用户");
		}
		
		// 是否存在被屏蔽用户
		if (userBaseService.getById(targetId) == null) {
			throw new JeecgBootException("被屏蔽的用户不存在");
		}
		
		RequirementsReviewsShields shields = new RequirementsReviewsShields();
		shields.setRequirementId(requirementId);
		shields.setShieldUid(targetId);
		shields.setCreateTime(new Date());
		
		boolean result = requirementsReviewsShieldsService.save(shields);
		return result ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "新增需求留言", notes = "C16 留言窗口 (联系需求方)")
	@PostMapping(value = "/requirements/review")
	public Result addRequirementReview(RequirementReviewDto reviewDto) {
		RequirementsReviews reviews = new RequirementsReviews();
		reviews.setUid(SecurityUtils.getCurrentUserUid());
		reviews.setCreateTime(new Date());
		reviews.setRequirementId(reviewDto.getRequirementId());
		reviews.setContent(reviewDto.getContent());
		
		boolean ok = requirementsReviewsService.save(reviews);
		if (ok) {
			// 添加需求留言次数
			requirementsService.incrementReviewCount(reviewDto.getRequirementId());
		}
		return ok ? Result.succ : Result.fail;
	}
}
