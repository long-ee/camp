package com.campgem.common.enums;

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

    ;


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
}
