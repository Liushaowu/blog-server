package com.blog.common.result;

/**
 * @Author: zhangyilei
 * @Date: 2019/1/17o
 * @Version 1.0.0
 * @Description: 返回统一编码枚举
 */
public enum ReturnStatusCode implements ReturnCode {

    /**
     * 0 失败
     */
    FAILED(0, "请求失败"),

    /**
     * 200请求成功
     */
    OK(200, "请求成功"),


    /**
     * 用户信息
     */
    TO_LOGIN(401, "请登录"),

    NOT_PERMISSION(402, "无访问权限"),

    USER_DELETED(403, "用户已删除"),

    RELOAD_USER_INFO(202, "请重新加载用户信息");

    /**
     * 请求参数格式错误
     */
    public static final int PARAMETER_VIOLATION = 603;
    /**
     * 请求参数为空
     */
    public static final int PARAMETER_EMPTY = 606;
    /**
     * 后台逻辑出错,请求调用失败状态吗
     */
    public static final int SERVER_ERROR_CODE = 500;
    /**
     * 服务调用失败
     */
    public static final int SERVICE_FAILED = 501;
    /**
     * 登录失败
     */
    public static final int ABNORMAL_LOGIN = 401;
    /**
     * 服务调用成功
     */
    public static final int SUCCESS = 200;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    ReturnStatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
