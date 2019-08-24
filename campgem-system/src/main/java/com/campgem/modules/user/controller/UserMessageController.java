package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.common.dto.AnnouncementSendModel;
import com.campgem.modules.common.entity.SysAnnouncement;
import com.campgem.modules.common.service.ISysAnnouncementSendService;
import com.campgem.modules.common.service.ISysAnnouncementService;
import com.campgem.modules.user.dto.UserMessageReplyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags="用户消息管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserMessageController {
    @Resource
    private ISysAnnouncementSendService sysAnnouncementSendService;
    @Resource
    private ISysAnnouncementService  sysAnnouncementService;

    @ApiOperation(value="用户消息管理-我的消息", notes="G11 我的消息")
    @GetMapping(value = "/user/message/list")
    public Result<IPage<AnnouncementSendModel>> getUserMessageList(AnnouncementSendModel announcementSendModel,
                                                                      @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Page<AnnouncementSendModel> page = new Page<>(pageNo, pageSize);
        IPage<AnnouncementSendModel> announcementSendModels = sysAnnouncementSendService.getMyAnnouncementSendPage(page, announcementSendModel);
        return new Result<IPage<AnnouncementSendModel>>().result(announcementSendModels);
    }

    @ApiOperation(value="用户消息管理-消息回复", notes="G111 消息回复")
    @GetMapping(value = "/user/message/reply")
    public Result messageReply(@Valid UserMessageReplyDto messageReplyDto) {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        messageReplyDto.setMemberId(memberId);
        sysAnnouncementService.messageReply(messageReplyDto);
        return Result.ok();
    }

}
