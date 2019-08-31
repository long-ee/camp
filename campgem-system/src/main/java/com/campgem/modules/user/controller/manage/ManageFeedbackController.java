package com.campgem.modules.user.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.user.dto.FeedbackReplyDto;
import com.campgem.modules.user.entity.Feedback;
import com.campgem.modules.user.service.IFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@Api(tags="反馈/举报管理接口")
@Slf4j
@RequestMapping("/api/manage/v1")
public class ManageFeedbackController {

    @Resource
    private IFeedbackService feedbackService;
    
    @ApiOperation(value = "用户举报/反馈分页查询", notes = "H2")
    @GetMapping("/feedback/list")
    @ApiImplicitParam(name = "status", value = "状态，0待回复，-1已回复", paramType = "query")
    public Result queryFeedbackPageList(Integer status,
                                        @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        LambdaQueryWrapper<Feedback> query = new LambdaQueryWrapper<>();
        query.orderByDesc(Feedback::getCreateTime);
        if (status != null) {
            query.eq(Feedback::getStatus, status);
        }
        IPage<Feedback> page = feedbackService.page(new Page<>(pageNo, pageSize), query);
        return new Result<IPage<Feedback>>().result(page);
    }
    
    @ApiOperation(value = "反馈/举报-通过id删除", notes = "H2")
    @DeleteMapping(value = "/feedback/delete")
    public Result<?> delete(@RequestParam(name = "id") String id) {
        try {
            feedbackService.removeById(id);
        } catch (Exception e) {
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }
    
    /**
     * 批量删除
     */
    @ApiOperation(value = "反馈/举报-批量删除", notes = "H2")
    @DeleteMapping(value = "/feedback/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids") String ids) {
        if (ids == null || "".equals(ids.trim())) {
            return Result.fail;
        } else {
            feedbackService.removeByIds(Arrays.asList(ids.split(",")));
            return Result.succ;
        }
    }

    @PostMapping(value = "/feedback/reply")
    @ApiOperation(value="反馈/举报-消息回复", notes="H21")
    public Result messageReply(@Valid FeedbackReplyDto feedbackReplyDto) {
        feedbackService.reply(feedbackReplyDto);
        return Result.ok();
    }



}
