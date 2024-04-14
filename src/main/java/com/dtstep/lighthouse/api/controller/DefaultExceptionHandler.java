package com.dtstep.lighthouse.api.controller;

import com.dtstep.lighthouse.api.common.ResultCode;
import com.dtstep.lighthouse.api.common.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Object globalErrorHandler(HttpServletRequest request, MethodArgumentNotValidException e)
    {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder message = new StringBuilder();
        for(int i=0;i< fieldErrors.size();i++){
            message.append(fieldErrors.get(i).getDefaultMessage());
            if(i < fieldErrors.size() - 1){
                message.append(";");
            }
        }
        logger.info(e.getMessage());
        return ResultData.result(message.toString());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Object handleException(HttpRequestMethodNotSupportedException ex) {
        logger.error("Process Error!",ex);
        return ResultData.result(ResultCode.GetNotSupport);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception ex) {
        logger.error("System Error!",ex);
        return ResultData.result(ex.getMessage());
    }

}
