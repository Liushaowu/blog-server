package com.blog.exception.aspect;

import com.blog.exception.RollBackException;

import java.lang.annotation.*;

/**
 * @Author: tangl
 * @Date: 2019/11/6 19:47
 * @Description:
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RollBackFlag {

    Class<? extends RollBackException> rollbackException() default RollBackException.class;

    int code() default 500;

    String message() default "服务异常";
}
