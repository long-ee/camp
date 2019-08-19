package com.campgem.modules.trade.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.aspect.annotation.AutoLog;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.trade.entity.OrdersGoods;
import com.campgem.modules.trade.service.IOrderGoodsService;
import io.swagger.annotations.Api;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="订单商品")
@RestController
@RequestMapping("/trade/orderGoods")
public class OrderGoodsController {
	@Autowired
	private IOrderGoodsService orderGoodsService;
	
	/**
	  * 分页列表查询
	 * @param ordersGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单商品-分页列表查询")
	@ApiOperation(value="订单商品-分页列表查询", notes="订单商品-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OrdersGoods>> queryPageList(OrdersGoods ordersGoods,
	                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
	                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
	                                                HttpServletRequest req) {
		Result<IPage<OrdersGoods>> result = new Result<IPage<OrdersGoods>>();
		QueryWrapper<OrdersGoods> queryWrapper = QueryGenerator.initQueryWrapper(ordersGoods, req.getParameterMap());
		Page<OrdersGoods> page = new Page<OrdersGoods>(pageNo, pageSize);
		IPage<OrdersGoods> pageList = orderGoodsService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param ordersGoods
	 * @return
	 */
	@AutoLog(value = "订单商品-添加")
	@ApiOperation(value="订单商品-添加", notes="订单商品-添加")
	@PostMapping(value = "/add")
	public Result<OrdersGoods> add(@RequestBody OrdersGoods ordersGoods) {
		Result<OrdersGoods> result = new Result<OrdersGoods>();
		try {
			orderGoodsService.save(ordersGoods);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param ordersGoods
	 * @return
	 */
	@AutoLog(value = "订单商品-编辑")
	@ApiOperation(value="订单商品-编辑", notes="订单商品-编辑")
	@PutMapping(value = "/edit")
	public Result<OrdersGoods> edit(@RequestBody OrdersGoods ordersGoods) {
		Result<OrdersGoods> result = new Result<OrdersGoods>();
		OrdersGoods ordersGoodsEntity = orderGoodsService.getById(ordersGoods.getId());
		if(ordersGoodsEntity ==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = orderGoodsService.updateById(ordersGoods);
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
	@AutoLog(value = "订单商品-通过id删除")
	@ApiOperation(value="订单商品-通过id删除", notes="订单商品-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			orderGoodsService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单商品-批量删除")
	@ApiOperation(value="订单商品-批量删除", notes="订单商品-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<OrdersGoods> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<OrdersGoods> result = new Result<OrdersGoods>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.orderGoodsService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单商品-通过id查询")
	@ApiOperation(value="订单商品-通过id查询", notes="订单商品-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OrdersGoods> queryById(@RequestParam(name="id",required=true) String id) {
		Result<OrdersGoods> result = new Result<OrdersGoods>();
		OrdersGoods ordersGoods = orderGoodsService.getById(id);
		if(ordersGoods ==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(ordersGoods);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<OrdersGoods> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              OrdersGoods ordersGoods = JSON.parseObject(deString, OrdersGoods.class);
              queryWrapper = QueryGenerator.initQueryWrapper(ordersGoods, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<OrdersGoods> pageList = orderGoodsService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单商品列表");
      mv.addObject(NormalExcelConstants.CLASS, OrdersGoods.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单商品列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
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
              List<OrdersGoods> listOrdersGoodsses = ExcelImportUtil.importExcel(file.getInputStream(), OrdersGoods.class, params);
              orderGoodsService.saveBatch(listOrdersGoodsses);
              return Result.ok("文件导入成功！数据行数:" + listOrdersGoodsses.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
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
