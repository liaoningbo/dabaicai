package com.dabaicai.common.response;

public enum ResponseCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String msg;

    ResponseCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }
}
