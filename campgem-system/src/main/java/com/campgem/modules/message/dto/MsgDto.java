package com.campgem.modules.message.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: X.Tony
 */
@Data
public class MsgDto {
    /**
     * 发送者
     */
    @ApiModelProperty(value = "发送者")
    private String sender;
    /**
     * 接收范围
     */
    @ApiModelProperty(value = "接收者范围")
    private String scope;
    /**
     * 接收者
     */
    @ApiModelProperty(value = "接收者")
    private String receiver;
    /**
     * 消息模板code
     */
    @ApiModelProperty(value = "消息类型")
    private String msgType;
    /**
     * 消息模板参数
     */
    @ApiModelProperty(value = "消息模板参数")
    private Object[] params;

    /**
     * 消息标题
     */
    private String msgTitle;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 是否需要组装(默认需要)
     */
    private boolean needAssemble = true;

}
