package com.campgem.modules.trade.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.BeanConvertUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

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
	                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<MGoodsListVo>> result = new Result<>();
		
		Page<MGoodsQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<MGoodsListVo> pageList = goodsService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 * 添加
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
	 */
	@ApiOperation(value = "商品-编辑", notes = "商品-编辑")
	@PutMapping(value = "/goods/edit")
	public Result edit(@Valid @RequestBody MGoodsVo goods) {
		boolean ok = goodsService.update(goods);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 通过id删除
	 */
	@ApiOperation(value = "商品-通过id删除", notes = "商品-通过id删除")
	@DeleteMapping(value = "/goods/delete")
	public Result<?> delete(@RequestParam(name = "id") String id) {
		try {
			goodsService.removeById(id);
		} catch (Exception e) {
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 */
	@ApiOperation(value = "商品-批量删除", notes = "商品-批量删除")
	@DeleteMapping(value = "/goods/deleteBatch")
	public Result<Goods> deleteBatch(@RequestParam(name = "ids") String ids) {
		Result<Goods> result = new Result<>();
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
	public Result<GoodsDetailVo> queryById(@RequestParam(name = "id") String id) {
		GoodsDetailVo goodsDetailVo = goodsService.queryGoodsDetail(id, false);
		return new Result<GoodsDetailVo>().result(goodsDetailVo);
	}
}
