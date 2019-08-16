package com.campgem.modules.trade.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.vo.GoodsCartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 购物车
 * @Author: ling
 * @Date: 2019-08-16
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "购物车管理接口")
public class CartController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private ICartService cartService;
	
	/**
	 * 添加商品到购物车
	 */
	@ApiOperation(value = "添加商品到购物车", notes = "C18 购物车")
	@PostMapping(value = "/cart")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID", paramType = "form", required = true),
			@ApiImplicitParam(name = "specId", value = "商品规格ID", paramType = "form"),
			@ApiImplicitParam(name = "quantity", value = "商品数量，默认1", defaultValue = "1", paramType = "form"),
	})
	public Result addGoodsToCard(String goodsId, String specId, Integer quantity) {
		if (StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException("商品ID不能为空");
		}
		Boolean result = cartService.addGoods(goodsId, specId, quantity);
		return result ? Result.succ : Result.fail;
	}
	
	/**
	 * 购物车删除商品
	 */
	@ApiOperation(value = "移除购物车商品", notes = "C18 购物车")
	@DeleteMapping(value = "/cart")
	@ApiImplicitParam(name = "cartId", value = "购物车ID", paramType = "form", required = true)
	public Result removeGoodsFromCard(String cartId) {
		Boolean result = cartService.removeCart(cartId);
		return result ? Result.succ : Result.fail;
	}
	
	/**
	 * 购物车列表
	 */
	@ApiOperation(value = "购物车列表", notes = "C18 购物车")
	@GetMapping(value = "/cart")
	public Result<Map<String, List<GoodsCartVo>>> queryCartList() {
		Map<String, List<GoodsCartVo>> data = cartService.queryCartList();
		Result<Map<String, List<GoodsCartVo>>> result = new Result<>();
		result.setResult(data);
		result.setSuccess(true);
		result.setCode(CommonConstant.SC_OK_200);
		return result;
	}
	
}
