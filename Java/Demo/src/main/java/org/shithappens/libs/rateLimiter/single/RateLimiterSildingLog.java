/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.rateLimiter.single;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author liangbo
 * @version V1.0
 * @Title: RateLimiterSildingLog.java
 * @Package org.shithappens.libs.rateLimiter.single
 * @Description 滑动日志
 * 下面这个实现其实不算严谨的滑动日志，更像一个把 1 秒时间切分成 1000 个时间窗口的滑动窗口算法。
 * @date 2022 07-22 15:20.
 */
public class RateLimiterSildingLog {

    /**
     * 阈值
     */
    private Integer qps = 2;
    /**
     * 记录请求的时间戳,和数量
     */
    private TreeMap<Long, Long> treeMap = new TreeMap<>();

    /**
     * 清理请求记录间隔, 60 秒
     */
    private long claerTime = 60 * 1000;

    public RateLimiterSildingLog(Integer qps) {
        this.qps = qps;
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 清理过期的数据老数据，最长 60 秒清理一次
        if (!treeMap.isEmpty() && (treeMap.firstKey() - now) > claerTime) {
            Set<Long> keySet = new HashSet<>(treeMap.subMap(0L, now - 1000).keySet());
            for (Long key : keySet) {
                treeMap.remove(key);
            }
        }
        // 计算当前请求次数
        int sum = 0;
        for (Long value : treeMap.subMap(now - 1000, now).values()) {
            sum += value;
        }
        // 超过QPS限制，直接返回 false
        if (sum + 1 > qps) {
            return false;
        }
        // 记录本次请求
        if (treeMap.containsKey(now)) {
            treeMap.compute(now, (k, v) -> v + 1);
        } else {
            treeMap.put(now, 1L);
        }
        return sum <= qps;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterSildingLog rateLimiterSildingLog = new RateLimiterSildingLog(3);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(250);
            LocalTime now = LocalTime.now();
            if (rateLimiterSildingLog.tryAcquire()) {
                System.out.println(now + " 做点什么");
            } else {
                System.out.println(now + " 被限流");
            }
        }
    }
}
