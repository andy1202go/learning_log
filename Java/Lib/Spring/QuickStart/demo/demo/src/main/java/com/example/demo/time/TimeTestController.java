/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * @author liangbo
 * @version V1.0
 * @Title: TestController.java
 * @Package com.example.demo.polymorphism
 * @Description
 * @date 2022 05-27 16:12.
 */
@RestController
@RequestMapping("/time")
@Valid
@Slf4j
public class TimeTestController {

    @RequestMapping(value = "")
    public String test() {
        englishToRealMonth();
        getNowMonthEnglishDesc();
        return "success";
    }

    private void englishToRealMonth() {
        String desc = "APRIL";
        Month month = Month.valueOf(desc);
        log.info("{} is {}", desc, month.toString());
    }
    
    private void getNowMonthEnglishDesc(){

        LocalDate localDate = LocalDate.now();
        Month month = localDate.getMonth();
        log.info("month name:{}",month.name());
        log.info("FULL:{}", month.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        log.info("NARROW:{}", month.getDisplayName(TextStyle.NARROW, Locale.ENGLISH));
        log.info("FULL_STANDALONE:{}",month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
        log.info("SHORT:{}",month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        log.info("SHORT_STANDALONE:{}",month.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH));
        log.info("NARROW_STANDALONE:{}",month.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.ENGLISH));
    }

}
