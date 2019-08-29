package com.campgem.modules.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.campgem.common.system.base.entity.JeecgEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 系统通告表
 * @Author: campgem
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Data
@TableName("sys_message")
public class SysMessage extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
//    @TableId(type = IdType.UUID)
//    private java.lang.String id;
    /**
     * 标题
     */
    @Excel(name = "msgTitle", width = 15)
    @ApiModelProperty(value = "msgTitle")
    private java.lang.String msgTitle;
    /**
     * 内容
     */
    @Excel(name = "msgContent", width = 15)
    @ApiModelProperty(value = "msgContent")
    private java.lang.String msgContent;
    /**
     * 发布人
     */
    @Excel(name = "sender", width = 15)
    @ApiModelProperty(value = "sender")
    private java.lang.String sender;
    /**
     * 发布名字
     */
    @Excel(name = "sender", width = 15)
    @ApiModelProperty(value = "sender")
    private java.lang.String senderName;
    /**
     * 接收者
     */
    @Excel(name = "receiver", width = 15)
    @ApiModelProperty(value = "receiver")
    private String receiver;
    /**
     * 消息类型1:通知公告2:系统消息
     */
    @Excel(name = "msgType", width = 15)
    @ApiModelProperty(value = "msgType")
    private java.lang.String msgType;
    /**
     * 通告对象范围（USER:指定用户，ALL:全体用户）
     */
    @Excel(name = "scope", width = 15)
    @ApiModelProperty(value = "scope")
    private java.lang.String scope;
    /**
     * 发布状态（0未发布，1已发布，2已撤销）
     */
    @Excel(name = "sendStatus", width = 15)
    @ApiModelProperty(value = "sendStatus", hidden = true)
    private java.lang.String sendStatus;
    /**发送时间*/
    @Excel(name = "发送时间", width = 15)
    @ApiModelProperty(value = "发送时间", hidden = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date sendTime;
    /**删除状态*/
    @Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态", hidden = true)
    @TableLogic
    private Integer delFlag;
    /**createBy*/
    @Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy", hidden = true)
    private String createBy;
    /**createTime*/
    @Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime", hidden = true)
    private java.util.Date createTime;
    /**updateTime*/
    @Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime", hidden = true)
    private java.util.Date updateTime;
    /**updateBy*/
    @Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy", hidden = true)
    private String updateBy;
}
