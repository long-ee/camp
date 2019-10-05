package com.campgem.modules.common.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.modules.common.dto.CategoryQueryDto;
import com.campgem.modules.common.entity.Category;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.common.vo.CategoryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@Api(tags="分类信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Resource
	private ICategoryService categoryService;

	@ApiOperation(value="分类信息-按照类别查询", notes="分类信息-详情查询")
	@GetMapping(value = "/category/type")
	@ApiImplicitParam(name = "type", value = "分类", required = true, paramType = "query")
	public Result<List<CategoryVo>> queryCategoryListByType(String type) {
		List<CategoryVo> list = categoryService.queryByType(type);
		return new Result<List<CategoryVo>>().result(list);
	}
}
