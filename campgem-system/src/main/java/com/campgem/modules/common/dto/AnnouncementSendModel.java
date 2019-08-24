package com.campgem.modules.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
public class AnnouncementSendModel implements Serializable {
    private static final long serialVersionUID = 1L;
	/**id*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**通告id*/
	private java.lang.String anntId;
	/**用户id*/
	private java.lang.String userId;
	/**标题*/
	private java.lang.String titile;
	/**内容*/
	private java.lang.String msgContent;
	/**发布人*/
	private java.lang.String sender;
	/**优先级（L低，M中，H高）*/
	private java.lang.String priority;
	/**阅读状态*/
	private java.lang.String readFlag;
	/**发布时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date sendTime;
    /** 消息类型 */
    private java.lang.String msgCategory;
}
