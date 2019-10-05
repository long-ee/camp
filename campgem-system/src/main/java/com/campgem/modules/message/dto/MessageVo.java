package com.campgem.modules.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel("消息展示实体")
public class MessageVo implements Serializable {
    private static final long serialVersionUID = 1L;
	/**通告id*/
	@ApiModelProperty(value = "通告id")
	private java.lang.String msgId;
	/** 消息类型 */
	@ApiModelProperty(value = "消息类型")
	private java.lang.String msgType;
	/**标题*/
	@ApiModelProperty(value = "标题")
	private java.lang.String msgTitle;
	/**内容*/
	@ApiModelProperty(value = "内容")
	private java.lang.String msgContent;
	/**发布人*/
	@ApiModelProperty(value = "发布人")
	private java.lang.String sender;
	@ApiModelProperty(value = "发布人名称")
	private java.lang.String senderName;
	@ApiModelProperty(value = "接收者会员ID")
	private java.lang.String memberId;
	/**阅读状态*/
	@ApiModelProperty(value = "阅读状态")
	private java.lang.String readFlag;
	/**发布时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发送时间")
	private java.util.Date sendTime;
	@ApiModelProperty(value = "拓展字段社团ID、商品ID等")
	private java.lang.String extend_1;

}
