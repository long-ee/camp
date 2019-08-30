package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.modules.user.dto.FeedbackDto;
import com.campgem.modules.user.service.IFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags="用户反馈/举报接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserFeedbackController {

    @Resource
    private IFeedbackService feedbackService;
    
    @ApiOperation(value = "用户举报/反馈", notes = "F15")
    @PostMapping("/user/feedback/report")
    public Result userFeedbackReport(@Valid @RequestBody FeedbackDto feedbackDto) {
        boolean ok = feedbackService.userFeedbackReport(feedbackDto);
        return ok ? Result.succ : Result.fail;
    }
}
