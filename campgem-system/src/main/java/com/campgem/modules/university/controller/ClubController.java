package com.campgem.modules.university.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.ClubAdminDto;
import com.campgem.modules.university.dto.ClubCreateOrUpdateDto;
import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import com.campgem.modules.user.vo.MemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@Api(tags="社团信息管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class ClubController {

	@Autowired
	private IClubService clubService;

	@ApiOperation(value="社团信息管理接口-社团列表分页查询", notes="E1 E13 社团列表(支持根据分类查询)")
	@GetMapping(value = "/university/club/list")
	public Result<IPage<ClubVo>> queryPageList(ClubQueryDto queryDto,
											 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											 HttpServletRequest req) {
		Page<ClubQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<ClubVo> clubVos = clubService.queryPageList(page, queryDto);
		return new Result<IPage<ClubVo>>().result(clubVos);
	}
	

	@ApiOperation(value="社团信息管理接口-社团详情查询", notes="E12 社团基本信息查询")
	@GetMapping(value = "/university/club/details")
	public Result<ClubVo> queryDetails(String clubId) {
		String memberId = SecurityUtils.getCurrentUserMemberId();
		ClubVo club = clubService.queryDetails(clubId);
		boolean isClubMember = clubService.isClubMember(clubId, memberId);
		club.setClubMember(isClubMember);
		return new Result<ClubVo>().result(club);
	}

	@ApiOperation(value="社团信息管理接口-创建社团", notes="G151 创建社团")
	@PostMapping(value = "/university/club/create")
	public Result create(@Valid ClubCreateOrUpdateDto createOrUpdateDto) {
		LoginUserVo loginUserVo = SecurityUtils.getCurrentUser();
		createOrUpdateDto.setCreatorId(loginUserVo.getMemberId());
		createOrUpdateDto.setUniversityId(loginUserVo.getUniversityId());
		clubService.createClub(createOrUpdateDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-编辑社团", notes="G151 编辑社团")
	@PostMapping(value = "/university/club/edit")
	public Result edit(@Valid ClubCreateOrUpdateDto createOrUpdateDto) {
		clubService.updateClub(createOrUpdateDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-加入社团", notes="E12 E13 加入社团")
	@PostMapping(value = "/university/club/join")
	public Result join(String clubId) {
		String memberId = SecurityUtils.getCurrentUserMemberId();
		ClubDto clubDto = new ClubDto();
		clubDto.setClubId(clubId);
		clubDto.setMemberId(memberId);
		clubService.joinClub(clubDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-退出社团", notes="E12 E13 退出社团")
	@PostMapping(value = "/university/club/dropOut")
	public Result dropOut(String clubId) {
		String memberId = SecurityUtils.getCurrentUserMemberId();
		ClubDto clubDto = new ClubDto();
		clubDto.setClubId(clubId);
		clubDto.setMemberId(memberId);
		clubService.dropOutClub(clubDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-社团管理员列表", notes="G154 新增/编辑管理员-社团管理员列表")
	@GetMapping(value = "/university/club/admin/list")
	public Result<List<MemberVo>> adminList(String clubId, boolean includePrimaryAdmin) {
		List<MemberVo> memberVos = clubService.listAdmin(clubId, includePrimaryAdmin);
		return new Result<List<MemberVo>>().result(memberVos);
	}

	@ApiOperation(value="社团信息管理接口-添加管理员", notes="G154 新增/编辑管理员-新增管理员")
	@PostMapping(value = "/university/club/admin/add")
	public Result addAdmin(@Valid ClubAdminDto clubAdminDto) {
		clubService.addAdmin(clubAdminDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-移除管理员", notes="G154 新增/编辑管理员-移除管理员")
	@PostMapping(value = "/university/club/admin/remove")
	public Result removeAdmin(@Valid ClubAdminDto clubAdminDto) {
		clubService.removeAdmin(clubAdminDto);
		return Result.ok();
	}

	@ApiOperation(value="社团信息管理接口-转让管理员", notes="G155 转让管理员")
	@PostMapping(value = "/university/club/admin/transfer")
	public Result transferAdmin(@Valid ClubAdminDto clubAdminDto) {
		clubService.transferAdmin(clubAdminDto);
		return Result.ok();
	}

}
