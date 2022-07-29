/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.message.internal.disruptor;

/**
 * @author 80338398
 * @version V1.0
 * @Title: DisruptorMqService.java
 * @Package com.example.demo.message.internal.disruptor
 * @Description
 * @date 2022 07-28 17:10.
 */
public interface DisruptorMqService {
    /**
     * 消息
     * @param message
     */
    void sayHelloMq(String message);
}
