package com.feng.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by rf on 2019/6/16.
 */
@ControllerAdvice
@Controller
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        log.error("{}",e.getMessage());
        //toDo
        return "/error/505.html";
    }
}
