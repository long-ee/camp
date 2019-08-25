package com.campgem.modules.university.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.vo.ClubActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Api(tags="【管理端】社团活动信息接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageClubActivityController {
	@Autowired
	private IClubActivityService clubActivityService;
	
	/**
	  * 分页列表查询
	 * @param queryDto
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="社团活动信息-分页列表查询", notes="E3")
	@GetMapping(value = "/club/activity/pageList")
	public Result<IPage<ClubActivityVo>> queryPageList(ClubActivityQueryDto queryDto,
													   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													   HttpServletRequest req) {
		Result<IPage<ClubActivityVo>> result = new Result<>();
		Page<ClubActivityQueryDto> page = new Page<ClubActivityQueryDto>(pageNo, pageSize);
		IPage<ClubActivityVo> pageList = clubActivityService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param clubActivity
	 * @return
	 */
	@ApiOperation(value="社团活动信息-添加", notes="E31")
	@PostMapping(value = "/club/activity/add")
	public Result<ClubActivity> add(ClubActivity clubActivity) {
		Result<ClubActivity> result = new Result<ClubActivity>();
		try {
			clubActivityService.save(clubActivity);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param clubActivity
	 * @return
	 */
	@ApiOperation(value="社团活动信息-编辑", notes="E31")
	@PostMapping(value = "/clubActivity/edit")
	public Result<ClubActivity> edit(ClubActivity clubActivity) {
		Result<ClubActivity> result = new Result<ClubActivity>();
		ClubActivity clubActivityEntity = clubActivityService.getById(clubActivity.getId());
		if(clubActivityEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = clubActivityService.updateById(clubActivity);
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
	@ApiOperation(value="社团活动信息-通过id删除", notes="E3")
	@PostMapping(value = "/clubActivity/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			clubActivityService.removeById(id);
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
	@ApiOperation(value="社团活动信息-详情查询", notes="E31")
	@GetMapping(value = "/clubActivity/details")
	public Result<ClubActivityVo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ClubActivityVo> result = new Result<ClubActivityVo>();
		ClubActivityVo clubActivity = clubActivityService.queryDetails(id);
		if(clubActivity==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(clubActivity);
			result.setSuccess(true);
		}
		return result;
	}
}
