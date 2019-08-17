package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.university.entity.enums.CategoryTypeEnum;
import com.campgem.modules.university.service.ICategoryService;
import com.campgem.modules.university.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
