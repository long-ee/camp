package com.campgem.modules.university.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.UniversityPosterDto;
import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.service.IUniversityPosterService;
import com.campgem.modules.university.vo.UniversityPosterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Slf4j
@Api(tags="海报信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class UniversityPosterController {

	@Resource
	private IUniversityPosterService universityPosterService;
	

	@ApiOperation(value="海报信息-海报列表查询", notes="E1 校园-海报列表")
	@GetMapping(value = "/university/poster/list")
	public Result<List<UniversityPosterVo>> list(UniversityPosterQueryDto queryDto) {
		List<UniversityPosterVo> universityPosterVos = universityPosterService.queryList(queryDto);
		return new Result<List<UniversityPosterVo>>().result(universityPosterVos);
	}


	@ApiOperation(value="海报信息-海报详情查询", notes="E11 海报详情")
	@GetMapping(value = "/university/poster/details")
	public Result<UniversityPosterVo> details(String posterId) {
		UniversityPosterVo universityPoster = universityPosterService.queryDetails(posterId);
		return new Result<UniversityPosterVo>().result(universityPoster);
	}
	

	@ApiOperation(value="海报信息-发布海报信息", notes="E111 G161 发布海报")
	@PostMapping(value = "/university/poster/publish")
	public Result<UniversityPosterVo> publish(@Valid UniversityPosterDto universityPosterDto) {
		String publisherId = SecurityUtils.getCurrentUserMemberId();
		universityPosterDto.setPublisherId(publisherId);
		universityPosterDto.setPublishTime(new Date());
		UniversityPosterVo universityPosterVo = universityPosterService.publish(universityPosterDto);
		return new Result<UniversityPosterVo>().result(universityPosterVo);
	}

	@ApiOperation(value="海报信息-编辑海报信息", notes="E111 G161 编辑海报信息")
	@PostMapping(value = "/university/poster/edit")
	public Result edit(@Valid UniversityPosterDto universityPosterDto) {
		universityPosterService.edit(universityPosterDto);
		return Result.ok();
	}

}
