/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.cache.caffeine;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CaffeineCacheService.java
 * @Package org.shithappens.libs.manualCache.caffeine
 * @Description CaffineTest
 * @date 2022 08-01 11:08.
 */
public class CaffeineCacheService extends CaffeineCacheGeneralService {
    private Cache<String, Object> manualCache;


    @PostConstruct
    public void init() {
        manualCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
    }

    /**
     * 手动加载
     *
     * @param key
     * @return
     */
    public Object manulOperator(String key) {
        //如果一个key不存在，那么会进入指定的函数生成value
        Object value = manualCache.get(key, t -> setValue(key));
        manualCache.put("hello", value);
        return value;
    }

    public Object getNullable(String key) {
        //判断是否存在如果不存返回null
        Object ifPresent = manualCache.getIfPresent(key);
        return ifPresent;
    }

    public void manualRemove(String key) {
        //移除一个key
        manualCache.invalidate(key);
    }

    /**
     * 同步加载
     * @param key
     * @return
     */
    public Object syncOperator(String key){
        LoadingCache<String, Object> syncCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> setValue(key).apply(key));
        return syncCache.get(key);
    }

    /**
     * 异步加载
     *
     * @param key
     * @return
     */
    public Object asyncOperator(String key){
        AsyncLoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> setAsyncValue(key).get());
        return cache.get(key);
    }

    public CompletableFuture<Object> setAsyncValue(String key){
        return CompletableFuture.supplyAsync(() -> {
            return key + "value";
        });
    }
}
