/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.event;

import com.example.demo.general.NotControllerResponseAdvice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ValidationTestController.java
 * @Package com.example.demo.validation
 * @Description validationTestController
 * <p>
 * 还存在一些问题没有关注
 * 比如：多个异步事件的消费顺序（实际无保证），异步耗时（据说不同环境不同）
 * 目前更适合确实不关心的异步，或者解耦的同步event的使用场景
 * <p>
 * spring观察者模式：https://time.geekbang.org/column/article/237810
 * <p>
 * 常见的几个spring event出现的问题：https://time.geekbang.org/column/article/370741，其中多个event处理，其中有处理失败影响后续的，应该有遇到
 * ——确保监听器的执行不会抛出异常。
 * ——使用ErrorHandler
 *
 * @date 2022 05-17 15:31.
 */
@RestController
@Valid
@RequestMapping("event")
public class EventTestController implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("")
    @NotControllerResponseAdvice
    public String test() {
        applicationContext.publishEvent(new SyncEvent(this, "test sync" + System.currentTimeMillis()));
        applicationContext.publishEvent(new AsyncEvent(this, "test async" + System.currentTimeMillis()));
        return "OK";
    }

    @PostMapping("/p")
    @NotControllerResponseAdvice
    public String testP() {
        eventPublisher.publishEvent(new SyncEvent(this, "test sync" + System.currentTimeMillis()));
        eventPublisher.publishEvent(new AsyncEvent(this, "test async" + System.currentTimeMillis()));
        return "OK";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = applicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, SimpleApplicationEventMulticaster.class);
        simpleApplicationEventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
    }
}
