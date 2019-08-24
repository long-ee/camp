package com.campgem.modules.university.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.entity.University;
import com.campgem.modules.user.entity.enums.MemberTypeEnum;
import com.campgem.modules.university.service.IUniversityService;
import com.campgem.modules.university.vo.UniversityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="【管理端】学校信息管理接口")
@Slf4j
@RequestMapping("/api/manage/v1")
public class ManageUniversityController {

	@Resource
	private IUniversityService universityService;

	/**
	 * 校园基本信息查询
	 * @param request
	 * @return
	 */
	@ApiOperation(value="Query university base information", notes="校园基本信息查询")
	@GetMapping(value = "/university/baseInfo")
	public Result<UniversityVo> queryUniversityBaseInfo(HttpServletRequest request) {
		Result<UniversityVo> result = new Result<>();
		LoginUserVo currentUser = SecurityUtils.getCurrentUser();
		// 如果当前用户为学生，则查询其所属学校信息
		if(StringUtils.equals(MemberTypeEnum.STUDENT.code(), currentUser.getMemberType())){
			UniversityVo universityVo = universityService.queryDetails(currentUser.getUniversityId());
			result.setResult(universityVo);
			result.setSuccess(true);
			return result;
		}
		result.setMessage("当前无法查询到用户匹配的校园信息");
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
	@ApiOperation(value="校园信息-分页列表查询", notes="校园信息-分页列表查询")
	@GetMapping(value = "/university/pageList")
	public Result<IPage<UniversityVo>> queryPageList(UniversityQueryDto queryDto,
												   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												   HttpServletRequest req) {
		Result<IPage<UniversityVo>> result = new Result<>();
		Page<UniversityQueryDto> page = new Page<UniversityQueryDto>(pageNo, pageSize);
		IPage<UniversityVo> pageList = universityService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param university
	 * @return
	 */
	@ApiOperation(value="校园信息-添加", notes="校园信息-添加")
	@PostMapping(value = "/university/add")
	public Result<University> add(@Valid University university) {
		Result<University> result = new Result<University>();
		try {
			universityService.save(university);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param university
	 * @return
	 */
	@ApiOperation(value="校园信息-编辑", notes="校园信息-编辑")
	@PostMapping(value = "/university/edit")
	public Result<University> edit(@Valid University university) {
		Result<University> result = new Result<University>();
		University universityEntity = universityService.getById(university.getId());
		if(universityEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = universityService.updateById(university);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@ApiOperation(value="校园信息-通过id删除", notes="校园信息-通过id删除")
	@PostMapping(value = "/university/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			universityService.removeById(id);
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
	@ApiOperation(value="校园信息-详情查询", notes="校园信息-详情查询")
	@GetMapping(value = "/university/details")
	public Result<UniversityVo> queryDetails(@RequestParam(name="id",required=true) String id) {
		Result<UniversityVo> result = new Result<UniversityVo>();
		UniversityVo university = universityService.queryDetails(id);
		if(university==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(university);
			result.setSuccess(true);
		}
		return result;
	}

}
