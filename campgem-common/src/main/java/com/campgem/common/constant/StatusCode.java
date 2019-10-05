package com.campgem.common.constant;

public class StatusCode {

    // general status code
    public static final int OK = 10200;
    public static final int BadRequest = 10400;
    public static final int Unauthorized = 10401;
    public static final int InvalidCredential = 10402;
    public static final int PermissionDenied = 10403;
    public static final int NotFound = 10404;
    public static final int InternalError = 10500;
    public static final int ServiceUnavailable = 10503;
    public static final int TimedOut = 10504;
    public static final int Conflict = 10506;
    public static final int UnknownError = 10600;

    // for login
    public static final int LoginFailed = 20601;
    public static final int LoginCredentialError = 20602;
    public static final int LoginIpBlockError = 20603;
    public static final int LoginUserLocked = 20604;
    public static final int ResetPasswordError = 20605;
    public static final int ResetPasswordBadError = 20606;
    public static final int NoSuchEmailExistError = 20607;
    public static final int UserStatusNotOkError = 20608;
    public static final int InvalidResetPasswordTokenError = 20609;

    // for club
    public static final int ClubNotExistError = 30601;
    public static final int ClubExistUserError = 30602;
    public static final int MemberIsAdmin = 30603;
    public static final int MemberIsAdminNotAllowDropOut = 30604;
    public static final int MemberIsPrimaryAdminNotAllowRemove = 30605;


}
