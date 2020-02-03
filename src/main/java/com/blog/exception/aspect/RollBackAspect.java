package com.blog.exception.aspect;

import com.blog.common.result.Result;
import com.blog.exception.RollBackException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * @Author: tangl
 * @Date: 2019/11/6 19:52
 * @Description:
 */
@Component
@Aspect
public class RollBackAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.blog.exception.aspect.RollBackFlag)")
    public void pointcut() {
    }

    /**
     * 后置异常通知
     * @param joinPoint
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "t")
    public void throwing(JoinPoint joinPoint, Throwable t) throws Throwable {
        // 是自定义异常直接抛出
        if (t instanceof RollBackException){
            throw t;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RollBackFlag rollBackFlag = method.getAnnotation(RollBackFlag.class);
        Class<? extends RollBackException> rollbackException = rollBackFlag.rollbackException();
        int resultCode = rollBackFlag.code();
        String resultMessage = rollBackFlag.message();
        Constructor<? extends RollBackException> constructor = rollbackException.getConstructor(Result.class);
        RollBackException rollBackException = constructor.newInstance(new Result<>(resultCode, resultMessage));
        throw rollBackException;
    }
}
