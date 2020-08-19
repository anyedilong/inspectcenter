package com.java.until;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.java.moudle.common.message.JsonResult;

@RestControllerAdvice
@SuppressWarnings("serial")
public class CommonException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    //自动校验字段非空返回信息
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult methodHandleException(MethodArgumentNotValidException exception) {
        String errorMessage = "field error";
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null)
                errorMessage = fieldError.getDefaultMessage();
        }
        logger.error("请求字段异常：" + errorMessage);
        return new JsonResult(null, 9001, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception exception) {
        //exception.printStackTrace();
        logger.error("异常：", exception);
        return new JsonResult(null, 9999, "系统错误！" + exception.getMessage());
    }
}
