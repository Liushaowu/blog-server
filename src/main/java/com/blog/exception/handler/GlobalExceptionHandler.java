package com.blog.exception.handler;


import com.blog.common.result.Result;
import com.blog.common.result.ReturnStatusCode;
import com.blog.exception.RollBackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author Liushaowu
 * @description 统一异常处理器
 * @create 2019/1/22
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String EX_UNKNOWN = "未知异常";

    /**
     * 事务回滚
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RollBackException.class)
    public Result transactionExceptionHandler(RollBackException e) {
        this.recordError(e);
        Result result = e.getResult();
        if (result!=null){
            result.setData(this.getStackTraceString(e));
            return result;
        }
        return new Result(ReturnStatusCode.SERVER_ERROR_CODE, e.getMessage(), this.getStackTraceString(e));
    }

    /**
     * 请求参数数据校验不通过，统一异常处理器
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result validExceptionHandler(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        String errorMsg = "请求参数数据校验不通过: ";
        for (ConstraintViolation constraintViolation : constraintViolations) {
            errorMsg += constraintViolation.getMessage();
        }
        this.recordError(exception);
        return new Result(ReturnStatusCode.PARAMETER_VIOLATION,errorMsg,this.getStackTraceString(exception));
    }

    /**
     * 后台校验未通过，统一异常处理器
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String errorMsg = "后台校验未通过: ";
        for (FieldError fieldError : fieldErrors) {
            errorMsg += fieldError.getDefaultMessage() + ", ";
        }
        errorMsg = errorMsg.substring(0,errorMsg.length()-2);
        recordError(exception);
        return new Result(ReturnStatusCode.PARAMETER_EMPTY,errorMsg,this.getStackTraceString(exception));
    }

    /**
     * 请求参数为空，统一异常处理器
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result validExceptionHandler(MissingServletRequestParameterException exception, WebRequest request, HttpServletResponse response) {
        String errorMsg = "请求参数为空: "+exception.getParameterType()+" "+exception.getParameterName();
        this.recordError(exception);
        return new Result(ReturnStatusCode.PARAMETER_EMPTY,errorMsg,this.getStackTraceString(exception));
    }

    /**
     * 其他非自定义异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception exception) {
        this.recordError(exception);
        return new Result(ReturnStatusCode.SERVICE_FAILED, EX_UNKNOWN, this.getStackTraceString(exception));
    }

    /**
     * 记录异常
     * @param e
     */
    private void recordError(Exception e){
        e.printStackTrace();
//        for (StackTraceElement stackTraceElement : e.getStackTrace()){
//            logger.error(stackTraceElement.toString());
//        }

    }
    private String getStackTraceString(Exception e){
        StringBuffer sb = new StringBuffer();
        for (StackTraceElement stackTraceElement : e.getStackTrace()){
            sb.append(stackTraceElement.toString()+"/n");
        }
        return sb.toString();
    }
}
