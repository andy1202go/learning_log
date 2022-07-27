/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author liangbo
 * @version V1.0
 * @Title: EventListener.java
 * @Package com.example.demo.event
 * @Description 事件监听器
 * @date 2022 07-27 11:41.
 */
@Slf4j
@Component
public class EventListener {

    @org.springframework.context.event.EventListener(SyncEvent.class)
    public void syncListener(SyncEvent syncEvent) {
        log.info("[syncListener] msg is {}", syncEvent.getMsg());
    }

    @org.springframework.context.event.EventListener(AsyncEvent.class)
    @Async
    public void asyncListener(AsyncEvent asyncEvent) {
        log.info("[asyncListener] msg is {}", asyncEvent.getMsg());
        throw new RuntimeException();
    }
}
