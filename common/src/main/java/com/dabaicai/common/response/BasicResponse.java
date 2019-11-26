package com.dabaicai.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

/**
 * 通用返回对象
 * created by lnb on 2019/10/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    public long code;
    public String msg;
    public String host;
    public ResponseData data;
    public static String hostName;
    static {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }catch (Exception e) {
            hostName = "unknown";
        }
    }

    /**
     * 成功返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse success(ResponseData data) {
        return new BasicResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), hostName, data);
    }

    /**
     * 成功返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse success() {
        return new BasicResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), hostName, null);
    }

    /**
     * 失败返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse failed(ResponseCode responseCode) {
        return new BasicResponse(responseCode.getCode(), responseCode.getMessage(), hostName, null);
    }

    /**
     * 失败返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse failed(String message) {
        return new BasicResponse(ResponseCode.FAILED.getCode(), message, hostName, null);
    }

    /**
     * 失败返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse failed() {
        return failed(ResponseCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse validateFailed() {
        return failed(ResponseCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse validateFailed(String message) {
        return new BasicResponse(ResponseCode.VALIDATE_FAILED.getCode(), message, hostName, null);
    }

    /**
     * 未登录返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse unauthorized() {
        return failed(ResponseCode.UNAUTHORIZED);
    }

    /**
     * 未授权返回结果
     * created by lnb on 2019/10/28
     */
    public static BasicResponse forbidden() {
        return failed(ResponseCode.FORBIDDEN);
    }
}