package com.campgem.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统错误码
 * @author: campgem
 */
public enum StatusEnum {

    // general error code
    OK(10200, "Succeeded", "成功"),
    BadRequest(10400, "Bad request", "请求错误"),
    Unauthorized(10401, "Unauthorized", "未授权的访问"),
    InvalidCredential(10402, "Invalid credential", "无效的授权"),
    PermissionDenied(10403, "Permission denied", "权限错误"),
    NotFound(10404, "Not found", "未找到"),
    InternalError(10500, "Internal server error", "服务内部错误"),
    ServiceUnavailable(10503, "Service unavailable", "服务不可用"),
    TimedOut(10504, "Timeout", "处理超时"),
    TooManyRequests(10505, "Too many requests", "请求过于频繁,请稍后重试"),
    Conflict(10506, "Conflict", "资源冲突"),
    UnknownError(10600, "Unknown error", "未知错误"),
    TimeFormatError(10601, "Time format error", "时间格式错误"),
    UserIdBlankError(10602, "The user id can't empty", "用户ID不能为空"),
    FeedbackCategoryUnknownError(10603, "Unknown feedback category", "未知的反馈分类"),

    // for login
    LoginFailed(20601, "Login failed", "登录信息无效"),
    LoginCredentialError(20602, "User not exists or invalid password", "用户不存在或密码错误"),
    LoginIpBlockError(20603, "Too many failed login attempts, please retry 10 minutes later", "登录错误次数过多,请10分钟后重试"),
    LoginUserLocked(20604, "User locked, please contact administrator", "用户锁定，请联系管理员"),
    ResetPasswordError(20605, "The old and new passwords are the same", "重置密码失败,旧密码与新密码相同"),
    ResetPasswordBadError(20606, "Two inconsistent passwords", "两次输入的密码不一致"),
    NoSuchEmailExistError(20607, "No such email exist", "邮箱不存在"),
    UserStatusNotOkError(20608, "User status invalid", "用户状态异常"),
    InvalidResetPasswordTokenError(20609, "Invalid credential to reset password", "重置密码凭证无效"),


    // for club
    ClubNotExistError(30601,"The club is not exist", "该社团不存在"),
    ClubExistUserError(30602, "The user is already a member of the club", "该用户已经是该社团成员"),
    MemberIsAdmin(30604, "The member is the administrator.", "该会员已经是本社团管理员"),
    MemberIsAdminNotAllowDropOut(30603, "You are the administrator. Please transfer the club and then withdraw or disband the club at the personal center.", "你是管理员，请转让社团后再退出或至个人中心解散社团"),
    MemberIsPrimaryAdminNotAllowRemove(30605, "The administrator is the primary administrator, not allow remove.", "该管理员是主管理员，不允许移除"),

    
    // for trade
    UnknownPaymentError(40601, "Unknown payment", "未知的支付方式"),
    GoodsIdAndCartIdBlankError(40602, "Goods id and card id can't empty together", "商品ID和购物车ID不能同时为空"),
    OrderPaidOrExpiredError(40603, "The order is paid or expired", "订单已支付或已过期"),
    GoodsNotExistError(40604, "The goods not exist", "商品不存在"),
    SpecificationBlankError(40605, "The goods specification can't empty", "商品规格不能为空"),
    SpecificationNotExistError(40606, "The specification not exist", "规格不存在"),
    SpecificationNotMatchGoodsError(40607, "The specification not belongs to goods", "规格与商品不匹配"),
    GoodsIdBlankError(40608, "The goods id can't empty", "商品ID不能为空"),
    GoodsShieldUsersError(40609, "Not a commodity publisher, you can't shield users", "不是商品发布者，不能屏蔽用户"),
    ShieldUserNotExistError(40610, "The shield user not exist", "被屏蔽的用户不存在"),
    RequirementImagesMaxError(40611, "Up to 3 requirement images", "需求图片最多3张"),
    RequirementNotExistError(40612, "The requirement not exist", "需求不存在"),
    RequirementShieldUsersError(40613, "Not a requirement publisher, you can't shield users", "不是需求发布者，不能屏蔽用户"),
    CartDataError(40614, "Cart data error", "购物车数据错误"),
    AppointmentTimeError(40615, "Reservation time format error", "预约时间格式错误"),
    OrderCreatedError(40616, "Order creation failed", "订单创建失败"),
    GoodsStockNotEnoughError(40617, "\"%s\" is under-stocked", "\"%s\"库存不足"),
    OrdersStatusError(40618, "Order status error", "订单状态错误"),
    OrdersNotExistError(40619, "Order not exist", "订单不存在"),
    GoodsEvaluationNotEmptyError(40620, "Goods evaluation can't empty", "商品评价不能为空"),
    OrdersOwnerError(40621, "You are not buyer or seller", "您不是买家或者卖家"),
    TrackingNumberEmptyError(40622, "Tracking number can't empty", "物流单号不能为空"),
    UserGoodsPropertyError(40623, "Goods properties not complete", "商品属性不全"),
    GoodsImagesEmptyError(40624, "Goods images can't empty", "商品图片不能为空"),
    GoodsOwnError(40625, "You are not the publisher of the goods", "您不是商品的发布者"),
    GoodsIsBelongYourselfError(40626, "You can't buy your goods", "不能购买您的商品"),
    CartEmptyError(40627, "Your cart is empty", "您的购物车没有商品"),
    GoodsStatusError(40628, "The goods '%s' is off sale", "商品'%s'不在销售状态"),
    ServiceStatusError(40628, "The service '%s' is disable", "服务'%s'已下架"),
    
    
    // for service
    ServiceNotExistError(50601, "The service is not exist", "该服务不存在"),
    ServiceImagesBlankError(50602, "The service images can't empty", "服务图片不能为空"),
    ServiceImagesMaxError(50603, "Up to 10 service images", "服务图片最多10张"),
    ActivityNotExistError(50604, "The activity is not exist", "该活动不存在"),
    ActivityImagesBlankError(50605, "The activity images can't empty", "活动图片不能为空"),
    ActivityNeedLocationBusiness(50606, "Only location business can publish", "只有本地商家才能发布"),
    
    ;

    private final String enableLanguage = "zh";
    private int code;
    private String enMsg;
    private String cnMsg;

    StatusEnum(int code, String enMsg, String cnMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.cnMsg = cnMsg;
    }

    private StatusEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public String msg() {
        if(StringUtils.equals(enableLanguage, "zh")){
            return cnMsg;
        }
        return enMsg;
    }

}
