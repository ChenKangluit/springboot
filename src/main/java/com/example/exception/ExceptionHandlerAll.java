package com.example.exception;



import com.example.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class ExceptionHandlerAll {

    @ExceptionHandler(ServiceException.class)
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }

}
