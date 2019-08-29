package com.campgem.modules.message.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.entity.enums.MsgTypeEnum;
import com.campgem.modules.message.service.ISysMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags="【管理端】消息管理接口")
@RestController
@RequestMapping("/api/manage/v1")
public class ManageMessageController {
	@Autowired
	private ISysMessageService sysMessageService;

	@GetMapping(value = "/message/list")
	@ApiOperation(value="消息管理-分页列表查询", notes="H1")
	public Result<IPage<SysMessage>> queryPageList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												   HttpServletRequest req) {
		Result<IPage<SysMessage>> result = new Result<>();
		LambdaQueryWrapper<SysMessage> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysMessage::getMsgType, MsgTypeEnum.NOTICE.code());
		Page<SysMessage> page = new Page<>(pageNo, pageSize);
		IPage<SysMessage> pageList = sysMessageService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param sysMessage
	 * @return
	 */
	@PostMapping(value = "/message/add")
	@ApiOperation(value="消息管理-新增通知", notes="H11")
	public Result<SysMessage> add(SysMessage sysMessage) {
		Result<SysMessage> result = new Result<SysMessage>();
		try {
			sysMessage.setMsgType(MsgTypeEnum.NOTICE.code());
			sysMessageService.sendNoticeMessage(sysMessage);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/message/delete")
	public Result<SysMessage> delete(@RequestParam(name="id",required=true) String id) {
		Result<SysMessage> result = new Result<SysMessage>();
		SysMessage sysAnnouncement = sysMessageService.getById(id);
		if(sysAnnouncement==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sysMessageService.removeById(sysAnnouncement);
			if(ok) {
				result.success("删除成功!");
			}
		}
		return result;
	}
	
}
