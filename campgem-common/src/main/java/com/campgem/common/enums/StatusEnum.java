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
