package com.blog.common.result;


/**
 * @author Liushaowu
 * @description 统一返回
 * @create 2019/10/25
 * @since 1.0.0
 */

public class Result<T> extends BaseResult {
    private static final long serialVersionUID = 1L;

    public static <T> Result<T> success() {
        return new Result<>(ReturnStatusCode.OK);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ReturnStatusCode.OK, data);
    }

    public static <T> Result<T> failed( String message) {
        return new Result<>(ReturnStatusCode.FAILED, message);
    }

    public Result(ReturnCode returnCode) {
        super(returnCode.getCode(), returnCode.getMessage());
    }

    public Result(ReturnCode returnCode, Object data) {
        super(returnCode.getCode(), returnCode.getMessage(), data);
    }

    public Result(Integer code, String message) {
        super(code, message);
    }

    public Result(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
