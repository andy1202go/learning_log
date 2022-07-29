/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.message.internal.disruptor;

import com.example.demo.general.NotControllerResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 粉碎机测试控制器
 * https://blog.csdn.net/weixin_48321993/article/details/125784008
 *
 * @author 80338398
 * @date 2022/07/27
 */
@RestController
@Valid
@RequestMapping("message/in/disruptor")
@Slf4j
public class DisruptorTestController {
    @Autowired
    private DisruptorMqService disruptorMqService;

    @PostMapping("")
    @NotControllerResponseAdvice
    public String test() {
        disruptorMqService.sayHelloMq("消息到了，Hello world!");
        log.info("消息队列已发送完毕");
        //这里停止2000ms是为了确定是处理消息是异步的
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }

}
