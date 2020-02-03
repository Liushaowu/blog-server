package com.blog.exception;

import com.blog.common.result.Result;

/**
 * @Author: hehaijin
 * @Date: 2019/3/7 10:57
 * @Version 1.0.0
 * @Description: 自定义事务回滚异常
 */
public class RollBackException extends RuntimeException {
    private Result result;
    public RollBackException() {
    }

    public RollBackException(Result result) {
        this.result = result;
    }

    public RollBackException(String message) {
        super(message);
    }

    public RollBackException(String message, Result result) {
        super(message);
        this.result = result;
    }

    public RollBackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollBackException(Throwable cause) {
        super(cause);
    }

    public RollBackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
