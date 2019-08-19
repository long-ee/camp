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
	

	@ApiOperation(value="海报信息-海报列表查询", notes="海报信息-海报列表查询")
	@GetMapping(value = "/university/poster/list")
	public Result<List<UniversityPosterVo>> list(UniversityPosterQueryDto queryDto) {
		List<UniversityPosterVo> universityPosterVos = universityPosterService.queryList(queryDto);
		return new Result<List<UniversityPosterVo>>().result(universityPosterVos);
	}


	@ApiOperation(value="海报信息-海报详情查询", notes="海报信息-海报详情查询")
	@GetMapping(value = "/university/poster/details")
	public Result<UniversityPosterVo> details(String posterId) {
		UniversityPosterVo universityPoster = universityPosterService.queryDetails(posterId);
		return new Result<UniversityPosterVo>().result(universityPoster);
	}
	

	@ApiOperation(value="海报信息-发布海报信息", notes="海报信息-发布海报信息")
	@PostMapping(value = "/university/poster/publish")
	public Result<UniversityPosterVo> publish(@Valid UniversityPosterDto universityPosterDto) {
		String publisherId = SecurityUtils.getCurrentUserMemberId();
		universityPosterDto.setPublisherId(publisherId);
		universityPosterDto.setPublishTime(new Date());
		UniversityPosterVo universityPosterVo = universityPosterService.publish(universityPosterDto);
		return new Result<UniversityPosterVo>().result(universityPosterVo);
	}


}
