package com.campgem.modules.trade.controller.manage;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.trade.dto.manage.MGoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.GoodsDetailVo;
import com.campgem.modules.trade.vo.manage.MBusinessListVo;
import com.campgem.modules.trade.vo.manage.MGoodsListVo;
import com.campgem.modules.trade.vo.manage.MGoodsVo;
import com.campgem.modules.university.entity.Member;
import com.campgem.modules.university.entity.enums.MemberTypeEnum;
import com.campgem.modules.university.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
 * @Description: 商品
 * @Author: campgem
 * @Date: 2019-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "商品")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageGoodsController {
	@Resource
	private IGoodsService goodsService;
	
	@Resource
	private IMemberService memberService;
	
	/**
	 * 分页列表查询
	 */
	@ApiOperation(value = "商品-分页列表查询", notes = "C1 商品管理")
	@GetMapping(value = "/goods/pageList")
	public Result<IPage<MGoodsListVo>> queryPageList(MGoodsQueryDto queryDto,
	                                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
	                                                 HttpServletRequest req) {
		Result<IPage<MGoodsListVo>> result = new Result<>();
		
		Page<MGoodsQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<MGoodsListVo> pageList = goodsService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 * 添加
	 *
	 * @param goods
	 * @return
	 */
	@ApiOperation(value = "商品-添加", notes = "商品-添加")
	@PostMapping(value = "/goods/add")
	public Result add(@Valid @RequestBody MGoodsVo goods) {
		boolean ok = goodsService.save(goods);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation("查询商家名列表")
	@GetMapping("/business/list")
	public Result<List<MBusinessListVo>> queryBusinessList() {
		LambdaQueryWrapper<Member> query = new LambdaQueryWrapper<>();
		query.eq(Member::getMemberType, MemberTypeEnum.LOCAL_BUSINESS.code())
				.or()
				.eq(Member::getMemberType, MemberTypeEnum.ONLINE_BUSINESS.code());
		List<Member> list = memberService.list(query);
		return new Result<List<MBusinessListVo>>().result(BeanConvertUtils.copyList(list, MBusinessListVo.class));
	}
	
	/**
	 * 编辑
	 *
	 * @param goods
	 * @return
	 */
	@ApiOperation(value = "商品-编辑", notes = "商品-编辑")
	@PutMapping(value = "/goods/edit")
	public Result edit(@Valid @RequestBody MGoodsVo goods) {
		boolean ok = goodsService.update(goods);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "商品-通过id删除", notes = "商品-通过id删除")
	@DeleteMapping(value = "/goods/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		try {
			goodsService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "商品-批量删除", notes = "商品-批量删除")
	@DeleteMapping(value = "/goods/deleteBatch")
	public Result<Goods> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<Goods> result = new Result<Goods>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.goodsService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 */
	@ApiOperation(value = "商品-通过id查询", notes = "商品-通过id查询")
	@GetMapping(value = "/goods/queryById")
	public Result<GoodsDetailVo> queryById(@RequestParam(name = "id", required = true) String id) {
		GoodsDetailVo goodsDetailVo = goodsService.queryGoodsDetail(id, false);
		return new Result<GoodsDetailVo>().result(goodsDetailVo);
	}
	
	/**
	 * 导出excel
	 */
	@RequestMapping(value = "/goods/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		// Step.1 组装查询条件
		QueryWrapper<Goods> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Goods goods = JSON.parseObject(deString, Goods.class);
				queryWrapper = QueryGenerator.initQueryWrapper(goods, request.getParameterMap());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<Goods> pageList = goodsService.list(queryWrapper);
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "商品列表");
		mv.addObject(NormalExcelConstants.CLASS, Goods.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品列表数据", "导出人:Jeecg", "导出信息"));
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
	@RequestMapping(value = "/goods/importExcel", method = RequestMethod.POST)
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
				List<Goods> listGoodss = ExcelImportUtil.importExcel(file.getInputStream(), Goods.class, params);
				goodsService.saveBatch(listGoodss);
				return Result.ok("文件导入成功！数据行数:" + listGoodss.size());
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
