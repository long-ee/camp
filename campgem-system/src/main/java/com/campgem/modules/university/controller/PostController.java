package com.campgem.modules.university.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.PostQueryDto;
import com.campgem.modules.university.service.IPostService;
import com.campgem.modules.university.vo.PostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags="版块信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class PostController {
	@Autowired
	private IPostService postService;

	@ApiOperation(value="版块信息管理-分页列表查询", notes="版块信息管理-分页列表查询")
	@GetMapping(value = "/post/list")
	public Result<IPage<PostVo>> queryPageList(PostQueryDto queryDto,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											   HttpServletRequest req) {
		Page<PostQueryDto> page = new Page<PostQueryDto>(pageNo, pageSize);
		IPage<PostVo> postVos = postService.queryPageList(page, queryDto);
		return new Result<IPage<PostVo>>().result(postVos);
	}
}
