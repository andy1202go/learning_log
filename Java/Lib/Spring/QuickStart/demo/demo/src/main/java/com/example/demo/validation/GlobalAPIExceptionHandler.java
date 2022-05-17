/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author liangbo
 * @version V1.0
 * @Title: GlobalAPIExceptionHandler.java
 * @Package com.example.demo.validation
 * @Description ControllerExceptionHandler
 * @date 2022 05-17 15:35.
 */
@ControllerAdvice
@Slf4j
public class GlobalAPIExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public String handle(Exception e) {
        log.error("", e);
        return "error";
    }
}
