package com.campgem.modules.university.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.entity.UniversityPoster;
import com.campgem.modules.university.service.IUniversityPosterService;
import com.campgem.modules.university.vo.UniversityPosterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@Api(tags="【管理端】海报信息管理接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageUniversityPosterController {

	@Resource
	private IUniversityPosterService universityPosterService;
	
	/**
	  * 分页列表查询
	 * @param queryDto
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="海报信息-分页列表查询", notes="海报信息-分页列表查询")
	@GetMapping(value = "/universityPoster/pageList")
	public Result<IPage<UniversityPosterVo>> queryPageList(UniversityPosterQueryDto queryDto,
														   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														   HttpServletRequest req) {
		Result<IPage<UniversityPosterVo>> result = new Result<>();
		Page<UniversityPosterQueryDto> page = new Page<UniversityPosterQueryDto>(pageNo, pageSize);
		IPage<UniversityPosterVo> pageList = universityPosterService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param universityPoster
	 * @return
	 */
	@ApiOperation(value="海报信息-添加", notes="海报信息-添加")
	@PostMapping(value = "/universityPoster/add")
	public Result<UniversityPoster> add(@Valid UniversityPoster universityPoster) {
		Result<UniversityPoster> result = new Result<UniversityPoster>();
		try {
			universityPosterService.save(universityPoster);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param universityPoster
	 * @return
	 */
	@ApiOperation(value="海报信息-编辑", notes="海报信息-编辑")
	@PostMapping(value = "/universityPoster/edit")
	public Result<UniversityPoster> edit(@Valid UniversityPoster universityPoster) {
		Result<UniversityPoster> result = new Result<UniversityPoster>();
		UniversityPoster universityPosterEntity = universityPosterService.getById(universityPoster.getId());
		if(universityPosterEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = universityPosterService.updateById(universityPoster);
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
	@ApiOperation(value="海报信息-通过id删除", notes="海报信息-通过id删除")
	@PostMapping(value = "/universityPoster/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			universityPosterService.removeById(id);
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
	@ApiOperation(value="海报信息-详情查询", notes="海报信息-详情查询")
	@GetMapping(value = "/universityPoster/details")
	public Result<UniversityPosterVo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<UniversityPosterVo> result = new Result<UniversityPosterVo>();
		UniversityPosterVo universityPoster = universityPosterService.queryDetails(id);
		if(universityPoster==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(universityPoster);
			result.setSuccess(true);
		}
		return result;
	}

}
