/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

import static org.shithappens.libs.TestingSet.EXPIRE_LONG;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CaffeineCacheExpireStrategies.java
 * @Package org.shithappens.libs.cache.caffeine
 * @Description 各种过期策略
 * @date 2022 08-01 11:21.
 */
public class CaffeineCacheExpireStrategies extends CaffeineCacheGeneralService {
    /**
     * 基于大小的过期方式
     */
    // 根据缓存的计数进行驱逐
    LoadingCache<String, Object> cacheNum = Caffeine.newBuilder()
            .maximumSize(10000)
            .build(key -> setValue(key).apply(key));


    // 根据缓存的权重来进行驱逐（权重只是用于确定缓存大小，不会用于决定该缓存是否被驱逐）
    LoadingCache<String, Object> cacheWeight = Caffeine.newBuilder()
            .maximumWeight(10000)
            .weigher((key, value) -> weight(String.valueOf(key), 0).weigh(String.valueOf(key), 0))
            .build(key -> setValue(key).apply(key));

    /**
     * 基于时间的过期方式
     */
    // 基于固定的到期策略进行退出
    //expireAfterAccess(long, TimeUnit):在最后一次访问或者写入后开始计时，在指定的时间后过期。假如一直有请求访问该key，那么这个缓存将一直不会过期。
    // expireAfterWrite(long, TimeUnit): 在最后一次写入缓存后开始计时，在指定的时间后过期。
    // expireAfter(Expiry): 自定义策略，过期时间由Expiry实现独自计算。缓存的删除策略使用的是惰性删除和定时删除。这两个删除策略的时间复杂度都是O(1)
    LoadingCache<String, Object> cacheAccessTime = Caffeine.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build(key -> setValue(key).apply(key));
    LoadingCache<String, Object> cacheWriteTime = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(key -> setValue(key).apply(key));
    // 基于不同的到期策略进行退出
    LoadingCache<String, Object> cacheAfterSelfDefinition = Caffeine.newBuilder()
            .expireAfter(new Expiry<String, Object>() {
                @Override
                public long expireAfterCreate(String key, Object value, long currentTime) {
                    return TimeUnit.SECONDS.toNanos(EXPIRE_LONG);
                }

                @Override
                public long expireAfterUpdate(String s, Object o, long l, long l1) {
                    return 0;
                }

                @Override
                public long expireAfterRead(String s, Object o, long l, long l1) {
                    return 0;
                }
            }).build(key -> setValue(key).apply(key));

    /**
     * 基于引用的过期方式
     */
    // 当key和value都没有引用时驱逐缓存
    LoadingCache<String, Object> cacheWeak = Caffeine.newBuilder()
            .weakKeys()
            .weakValues()
            .build(key -> setValue(key).apply(key));

    // 当垃圾收集器需要释放内存时驱逐
    LoadingCache<String, Object> cacheSoft = Caffeine.newBuilder()
            .softValues()
            .build(key -> setValue(key).apply(key));


}
