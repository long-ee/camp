package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.dto.RequirementReviewDto;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.entity.RequirementsReviews;
import com.campgem.modules.trade.service.IRequirementsReviewsService;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.university.entity.enums.CategoryTypeEnum;
import com.campgem.modules.university.service.ICategoryService;
import com.campgem.modules.university.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
		IPage<RequirementsVo> pageList = requirementsService.queryPageList(queryDto, pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
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
