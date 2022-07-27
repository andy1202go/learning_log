/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author liangbo
 * @version V1.0
 * @Title: SyncEvent.java
 * @Package com.example.demo.event
 * @Description 同步事件
 * @date 2022 07-27 11:38.
 */
public class SyncEvent extends ApplicationEvent {
    private String msg;

    public SyncEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
