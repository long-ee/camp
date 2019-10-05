package com.campgem.modules.bbs.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.bbs.dto.PostQueryDto;
import com.campgem.modules.bbs.entity.Post;
import com.campgem.modules.bbs.service.IPostService;
import com.campgem.modules.bbs.vo.PostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags="【管理端】版块信息管理接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManagePostController {
	@Autowired
	private IPostService postService;
	
	/**
	  * 分页列表查询
	 * @param queryDto
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="版块信息-分页列表查询", notes="D1")
	@GetMapping(value = "/post/pageList")
	public Result<IPage<PostVo>> queryPageList(PostQueryDto queryDto,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											   HttpServletRequest req) {
		Result<IPage<PostVo>> result = new Result<>();
		Page<PostQueryDto> page = new Page<PostQueryDto>(pageNo, pageSize);
		IPage<PostVo> pageList = postService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}


	@ApiOperation(value="版块信息管理-分页列表查询", notes="D21 查询所属版块列表")
	@GetMapping(value = "/post/list")
	public Result<List<Post>> list() {
		LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Post::getEnabled, "1");
		List<Post> postList = postService.list(queryWrapper);
		return new Result<List<Post>>().result(postList);
	}
	
	/**
	  *   添加
	 * @param post
	 * @return
	 */
	@ApiOperation(value="版块信息-添加", notes="D11 版块信息-添加")
	@PostMapping(value = "/post/add")
	public Result<Post> add(@Valid Post post) {
		Result<Post> result = new Result<Post>();
		try {
			postService.save(post);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param post
	 * @return
	 */
	@ApiOperation(value="版块信息-编辑", notes="D11 版块信息-编辑")
	@PostMapping(value = "/post/edit")
	public Result<Post> edit(@Valid Post post) {
		Result<Post> result = new Result<Post>();
		Post postEntity = postService.getById(post.getId());
		if(postEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = postService.updateById(post);
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
	@ApiOperation(value="版块信息-通过id删除", notes="D1 版块信息-通过id删除")
	@PostMapping(value = "/post/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			postService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}


	/**
	 *   启用
	 * @param id
	 * @return
	 */
	@ApiOperation(value="版块信息-版块启用", notes="D1 版块启用")
	@PostMapping(value = "/post/enable")
	public Result<?> enable(@RequestParam(name="id",required=true) String id) {
		Result<Post> result = new Result<Post>();
		Post postEntity = postService.getById(id);
		if(postEntity==null) {
			result.error500("未找到对应实体");
		}else {
			postEntity.setEnabled("1");
			boolean ok = postService.updateById(postEntity);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("启用成功!");
			}
		}

		return result;
	}

	/**
	 *   禁用
	 * @param id
	 * @return
	 */
	@ApiOperation(value="版块信息-版块禁用", notes="D1 版块禁用")
	@PostMapping(value = "/post/disable")
	public Result<?> disable(@RequestParam(name="id",required=true) String id) {
		Result<Post> result = new Result<Post>();
		Post postEntity = postService.getById(id);
		if(postEntity==null) {
			result.error500("未找到对应实体");
		}else {
			postEntity.setEnabled("0");
			boolean ok = postService.updateById(postEntity);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("禁用成功!");
			}
		}
		return result;
	}


	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value="版块信息-详情查询", notes=" D11 版块信息-详情查询")
	@GetMapping(value = "/post/details")
	public Result<PostVo> queryDetails(@RequestParam(name="id",required=true) String id) {
		Result<PostVo> result = new Result<PostVo>();
		PostVo post = postService.queryDetails(id);
		if(post==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(post);
			result.setSuccess(true);
		}
		return result;
	}
}
