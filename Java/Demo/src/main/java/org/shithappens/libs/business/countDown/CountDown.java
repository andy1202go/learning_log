/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.business.countDown;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CountDown.java
 * @Package org.shithappens.libs.business.countDown
 * @Description 倒计时场景
 * @date 2022 07-25 15:05.
 */
public class CountDown {
    /**
     * 结论
     *
     *
     *
     * 总结了几点如下：
     *
     * 首先推荐使用 RocketMQ、Pulsar 等拥有定时投递功能的消息队列。
     *
     * 在不方便获得专业消息队列时可以考虑使用 Redisson DelayQueue 等基于 Redis 的延时队列方案，但要为 Redis 崩溃等情况设计补偿保护机制。
     * （在数据库索引设计良好的情况下，定时扫描数据库中未完成的订单产生的开销并没有想象中那么大。）
     *
     * 在无法使用 Redisson DelayQueue 等方案时可以考虑使用时间轮。由于时间轮重启远比 Redis 重启要频繁，定时扫库等保护机制更为重要。
     *
     * 永远不要使用 Redis 过期监听实现定时任务。
     */

    /**
     * https://mp.weixin.qq.com/s/9S59l2-nhYLlsH9hedlOwg 领导：谁再用Redis过期监听实现定时任务，立马滚蛋！
     *
     */

    /**
     * 之前搞过一个case，当时调研和最终采纳如下
     * 1、大多数倒计时都是前端在做，有组件，然后时间到了之后，是无法正常点击发送请求的；
     * 2、以上场景，后端是否做了逻辑兜底，不确定
     * 3、后端可能使用的方案：redis键过期监听，job定时去跑
     *
     * 采纳：
     * 1、分析认为业务对时间要求没那么强；
     * 2、redis过期监听不可靠；
     * 所以使用了job+zset保存时间戳的方案——从上面文章来看，就是Redisson DelayQueue的实现方式
     */
}
