package com.campgem.modules.university.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.service.IUniversityService;
import com.campgem.modules.university.vo.UniversityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags="学校信息管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UniversityController {

	@Resource
	private IUniversityService universityService;


	@ApiOperation(value="学校信息管理接口-学校列表查询", notes="B1 首页-学校列表查询")
	@GetMapping(value = "/university/list")
	public Result<List<UniversityVo>> list(UniversityQueryDto queryDto) {
		List<UniversityVo> universityVos = universityService.queryList(queryDto);
		return new Result<List<UniversityVo>>().result(universityVos);
	}


	@ApiOperation(value="学校信息管理接口-根据学校ID查询学校基本信息", notes="E1 校园-校园基本信息查询")
	@GetMapping(value = "/university/details")
	public Result<UniversityVo> queryDetails(String universityId) {
		UniversityVo university = universityService.queryDetails(universityId);
		return new Result<UniversityVo>().result(university);
	}

}
