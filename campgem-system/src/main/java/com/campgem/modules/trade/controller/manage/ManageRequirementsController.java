package com.campgem.modules.trade.controller.manage;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.trade.dto.manage.MRequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.manage.MRequirementsDetailVo;
import com.campgem.modules.trade.vo.manage.MRequirementsListVo;
import com.campgem.modules.trade.vo.manage.MRequirementsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "【管理端】需求信息管理接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageRequirementsController {
	@Autowired
	private IRequirementsService requirementsService;
	
	/**
	 * 分页列表查询
	 */
	@ApiOperation(value = "需求-分页列表查询", notes = "需求-分页列表查询")
	@GetMapping(value = "/requirement/list")
	public Result<IPage<MRequirementsListVo>> queryPageList(MRequirementsQueryDto queryDto,
	                                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
	                                                        HttpServletRequest req) {
		Result<IPage<MRequirementsListVo>> result = new Result<>();
		
		Page<MRequirementsListVo> page = new Page<>(pageNo, pageSize);
		IPage<MRequirementsListVo> pageList = requirementsService.queryManagePageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation("修改状态")
	@PostMapping("/requirement/status")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "需求ID", required = true),
			@ApiImplicitParam(name = "status", value = "状态，不区分大小写", allowableValues = "ENABLE, DISABLE", required = true)
	})
	public Result updateStatus(String id, String status) {
		boolean ok = requirementsService.updateStatusById(id, status);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 添加
	 *
	 * @param requirements
	 * @return
	 */
	@ApiOperation(value = "需求-添加", notes = "C31 需求-添加")
	@PostMapping(value = "/requirement/add")
	public Result add(@Valid @RequestBody MRequirementsVo requirements) {
		boolean ok = requirementsService.save(requirements);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 编辑
	 *
	 * @param requirements
	 * @return
	 */
	@ApiOperation(value = "需求-编辑，如果图片未更改，则把图片ID，图片需求ID一起上传", notes = "C31 需求-编辑")
	@PutMapping(value = "/requirement/edit")
	public Result edit(@Valid @RequestBody MRequirementsVo requirements) {
		boolean ok = requirementsService.update(requirements);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 通过id删除
	 */
	@ApiOperation(value = "需求-通过id删除", notes = "需求-通过id删除")
	@DeleteMapping(value = "/requirement/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		try {
			requirementsService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 */
	@ApiOperation(value = "需求-批量删除", notes = "需求-批量删除")
	@DeleteMapping(value = "/requirement/deleteBatch")
	@ApiImplicitParam(name = "ids", value = "已,分隔", required = true, paramType = "query")
	public Result<Requirements> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<Requirements> result = new Result<Requirements>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.requirementsService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "需求-通过id查询", notes = "需求-通过id查询")
	@GetMapping(value = "/requirement/queryById")
	public Result<MRequirementsDetailVo> queryById(@RequestParam(name = "id", required = true) String id) {
		MRequirementsDetailVo detail = requirementsService.queryManageRequirementDetail(id);
		return new Result<MRequirementsDetailVo>().result(detail);
	}
	
	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/requirement/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		// Step.1 组装查询条件
		QueryWrapper<Requirements> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Requirements requirements = JSON.parseObject(deString, Requirements.class);
				queryWrapper = QueryGenerator.initQueryWrapper(requirements, request.getParameterMap());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<Requirements> pageList = requirementsService.list(queryWrapper);
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "需求列表");
		mv.addObject(NormalExcelConstants.CLASS, Requirements.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("需求列表数据", "导出人:Jeecg", "导出信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
	}
	
	/**
	 * 通过excel导入数据
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/requirement/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<Requirements> listRequirementss = ExcelImportUtil.importExcel(file.getInputStream(), Requirements.class, params);
				requirementsService.saveBatch(listRequirementss);
				return Result.ok("文件导入成功！数据行数:" + listRequirementss.size());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return Result.error("文件导入失败:" + e.getMessage());
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Result.ok("文件导入失败！");
	}
	
}
