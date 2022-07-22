/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.rateLimiter.single;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liangbo
 * @version V1.0
 * @Title: RateLimiterGuava.java
 * @Package org.shithappens.libs.rateLimiter.distributed
 * @Description 基于Guava的令牌桶限流器
 * @date 2022 07-22 15:24.
 */
public class RateLimiterGuava {
    public static void main(String[] args) throws InterruptedException {
        // qps 2
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            System.out.println(time + ":" + rateLimiter.tryAcquire());
            Thread.sleep(250);
        }
    }
}
