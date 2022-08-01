/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.cache.caffeine;

import com.github.benmanes.caffeine.cache.Weigher;

import java.util.function.Function;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CaffeineCacheGeneralService.java
 * @Package org.shithappens.libs.cache.caffeine
 * @Description
 * @date 2022 08-01 11:33.
 */
public class CaffeineCacheGeneralService {
    public Function<String, Object> setValue(String key) {
        return t -> key + "value";
    }

    public Weigher<String, Integer> weight(String key, Integer value) {
        return (k, v) -> key.hashCode() + value;
    }
}
