package com.campgem.modules.university.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.aspect.annotation.AutoLog;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.entity.ClubMember;
import com.campgem.modules.university.service.IClubMemberService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@Api(tags="社团信息管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class ClubController {
	@Autowired
	private IClubService clubService;
	@Resource
	private IClubMemberService clubMemberService;

	/**
	 *   添加
	 * @param clubMember
	 * @return
	 */
	@ApiOperation(value="会员加入社团接口", notes="会员加入社团接口")
	@PostMapping(value = "/club/member/add")
	public Result<ClubMember> add(@Valid ClubMember clubMember) {
		Result<ClubMember> result = new Result<ClubMember>();
		try {
			clubMemberService.save(clubMember);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
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
	@ApiOperation(value="Query club information page list", notes="社团信息-分页列表查询")
	@GetMapping(value = "/club/pageList")
	public Result<IPage<ClubVo>> queryPageList(ClubQueryDto queryDto,
											 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											 HttpServletRequest req) {
		Result<IPage<ClubVo>> result = new Result<>();
		Page<ClubQueryDto> page = new Page<ClubQueryDto>(pageNo, pageSize);
		IPage<ClubVo> pageList = clubService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param club
	 * @return
	 */
	@ApiOperation(value="社团信息-添加", notes="社团信息-添加")
	@PostMapping(value = "/club/add")
	public Result<Club> add(@Valid Club club) {
		Result<Club> result = new Result<Club>();
		try {
			clubService.save(club);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param club
	 * @return
	 */
	@ApiOperation(value="社团信息-编辑", notes="社团信息-编辑")
	@PostMapping(value = "/club/edit")
	public Result<Club> edit(@Valid Club club) {
		Result<Club> result = new Result<Club>();
		Club clubEntity = clubService.getById(club.getId());
		if(clubEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = clubService.updateById(club);
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
	@ApiOperation(value="社团信息-通过id删除", notes="社团信息-通过id删除")
	@PostMapping(value = "/club/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			clubService.removeById(id);
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
	@ApiOperation(value="社团信息-详情查询", notes="社团信息-详情查询")
	@GetMapping(value = "/club/details")
	public Result<ClubVo> queryDetails(@RequestParam(name="id",required=true) String id) {
		Result<ClubVo> result = new Result<ClubVo>();
		ClubVo club = clubService.queryDetails(id);
		if(club==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(club);
			result.setSuccess(true);
		}
		return result;
	}
}
