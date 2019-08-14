package com.campgem.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {
    /**
     * 加密方式
     */
    private final static String algorithmName = "md5";

    /**
     * 默认加密次数值
     */
    private final static int DEFAULT_HASH_TIMES = 2;

    /**
     * 平台系统密码加密工具方法
     * @param username
     * @param password
     * @param times
     * @return
     */
    public static String encryptPassword(String username, String password , int times) {
        if(times == 0 || times < 0){
            times = DEFAULT_HASH_TIMES;
        }
        //加盐
        ByteSource salt = ByteSource.Util.bytes(username);
        //加密
        String newPassword = new SimpleHash(algorithmName, password, salt , times).toHex();
        return newPassword;
    }

    public static void main(String[] args) {
        String simpleHash = encryptPassword("wlcloudy","123456",2);
        System.out.println("加密后的值----->" + simpleHash);
    }
}
