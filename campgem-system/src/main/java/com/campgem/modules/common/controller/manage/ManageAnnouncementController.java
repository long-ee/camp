package com.campgem.modules.common.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonSendStatus;
import com.campgem.modules.common.entity.SysAnnouncement;
import com.campgem.modules.common.entity.enums.MsgCategoryEnum;
import com.campgem.modules.common.entity.enums.MsgTypeEnum;
import com.campgem.modules.common.service.ISysAnnouncementSendService;
import com.campgem.modules.common.service.ISysAnnouncementService;
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
public class ManageAnnouncementController {
	@Autowired
	private ISysAnnouncementService sysAnnouncementService;

	@GetMapping(value = "/message/list")
	@ApiOperation(value="消息管理-分页列表查询", notes="H1")
	public Result<IPage<SysAnnouncement>> queryPageList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                        HttpServletRequest req) {
		Result<IPage<SysAnnouncement>> result = new Result<>();
		LambdaQueryWrapper<SysAnnouncement> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysAnnouncement::getMsgCategory, MsgCategoryEnum.SYSTEM.code());
		Page<SysAnnouncement> page = new Page<>(pageNo, pageSize);
		IPage<SysAnnouncement> pageList = sysAnnouncementService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param sysAnnouncement
	 * @return
	 */
	@PostMapping(value = "/message/add")
	@ApiOperation(value="消息管理-分页列表查询", notes="H11")
	public Result<SysAnnouncement> add(SysAnnouncement sysAnnouncement) {
		Result<SysAnnouncement> result = new Result<SysAnnouncement>();
		try {
			sysAnnouncement.setMsgType(MsgTypeEnum.USER_TYPE.code());
			sysAnnouncement.setMsgCategory(MsgCategoryEnum.SYSTEM.code());
			sysAnnouncement.setSendStatus(CommonSendStatus.PUBLISHED_STATUS_1);//已发布
			sysAnnouncementService.saveAnnouncement(sysAnnouncement);
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
	public Result<SysAnnouncement> delete(@RequestParam(name="id",required=true) String id) {
		Result<SysAnnouncement> result = new Result<SysAnnouncement>();
		SysAnnouncement sysAnnouncement = sysAnnouncementService.getById(id);
		if(sysAnnouncement==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sysAnnouncementService.removeById(sysAnnouncement);
			if(ok) {
				result.success("删除成功!");
			}
		}
		return result;
	}
	
}
