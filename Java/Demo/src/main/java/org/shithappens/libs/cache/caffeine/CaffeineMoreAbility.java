/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CaffeineMoreAbility.java
 * @Package org.shithappens.libs.cache.caffeine
 * @Description 更多能力
 * @date 2022 08-01 11:28.
 */
public class CaffeineMoreAbility extends CaffeineCacheGeneralService {
    /**
     * 移除事件监听
     */
    Cache<String, Object> cacheListener = Caffeine.newBuilder()
            .removalListener((String key, Object value, RemovalCause cause) ->
                    System.out.printf("Key %s was removed (%s)%n", key, cause))
            .build();

    /**
     * 写入外部存储
     */
    LoadingCache<String, Object> cacheLogout = Caffeine.newBuilder()
            .writer(new CacheWriter<String, Object>() {
                @Override public void write(String key, Object value) {
                    // 写入到外部存储
                }
                @Override public void delete(String key, Object value, RemovalCause cause) {
                    // 删除外部存储
                }
            })
            .build(key ->  setValue(key).apply(key));

    /**
     * 统计
     * 
     * hitRate(): 返回缓存命中率
     *
     * evictionCount(): 缓存回收数量
     *
     * averageLoadPenalty(): 加载新值的平均时间
     */
    Cache<String, Object> cacheStat = Caffeine.newBuilder()
            .maximumSize(10_000)
            .recordStats()
            .build();

}
