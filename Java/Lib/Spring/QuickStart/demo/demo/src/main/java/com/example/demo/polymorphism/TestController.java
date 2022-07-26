/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.polymorphism;

import com.example.demo.general.NotControllerResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liangbo
 * @version V1.0
 * @Title: TestController.java
 * @Package com.example.demo.polymorphism
 * @Description
 * @date 2022 05-27 16:12.
 */
@RestController
@RequestMapping("/grab")
@Valid
@Slf4j
public class TestController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "")
    @NotControllerResponseAdvice
    public String doSyncGrab() {
        return getService("One").getSth();
    }

    private PolyTestApi getService(String nu){
        return (PolyTestApi) applicationContext.getBean(nu+"PolyTestApi");
    }
}
