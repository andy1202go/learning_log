/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.message.internal.disruptor;

import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Component;

/**
 * @author liangbo
 * @version V1.0
 * @Title: HelloEventFactory.java
 * @Package com.example.demo.message.internal
 * @Description
 * @date 2022 07-28 17:07.
 */
@Component
public class HelloEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
