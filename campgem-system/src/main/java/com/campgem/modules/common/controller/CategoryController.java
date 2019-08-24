package com.campgem.modules.common.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.modules.common.dto.CategoryQueryDto;
import com.campgem.modules.common.entity.Category;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.common.vo.CategoryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@Api(tags="分类信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Resource
	private ICategoryService categoryService;
	
	/**
	  * 分页列表查询
	 * @param queryDto
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="分类信息-分页列表查询", notes="分类信息-分页列表查询")
	@GetMapping(value = "/category/pageList")
	public Result<IPage<CategoryVo>> queryPageList(CategoryQueryDto queryDto,
												   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												   HttpServletRequest req) {
		Result<IPage<CategoryVo>> result = new Result<>();
		Page<CategoryQueryDto> page = new Page<CategoryQueryDto>(pageNo, pageSize);
		IPage<CategoryVo> pageList = categoryService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param category
	 * @return
	 */
	@ApiOperation(value="分类信息-添加", notes="分类信息-添加")
	@PostMapping(value = "/category/add")
	public Result<Category> add(@Valid Category category) {
		Result<Category> result = new Result<Category>();
		try {
			categoryService.save(category);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param category
	 * @return
	 */
	@ApiOperation(value="分类信息-编辑", notes="分类信息-编辑")
	@PostMapping(value = "/category/edit")
	public Result<Category> edit(@Valid Category category) {
		Result<Category> result = new Result<Category>();
		Category categoryEntity = categoryService.getById(category.getId());
		if(categoryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = categoryService.updateById(category);
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
	@ApiOperation(value="分类信息-通过id删除", notes="分类信息-通过id删除")
	@PostMapping(value = "/category/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			categoryService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 详情查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value="分类信息-详情查询", notes="分类信息-详情查询")
	@GetMapping(value = "/category/details")
	public Result<CategoryVo> queryDetails(@RequestParam(name="id",required=true) String id) {
		Result<CategoryVo> result = new Result<CategoryVo>();
		CategoryVo category = categoryService.queryDetails(id);
		if(category==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(category);
			result.setSuccess(true);
		}
		return result;
	}
}
