/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.cache.caffeine;

import com.example.demo.general.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liangbo
 * @version V1.0
 * @Title: CaffeineService.java
 * @Package com.example.demo.cache.caffeine
 * @Description
 * 注意过期策略和淘汰策略是两个东西
 * 按照个人理解，
 *      过期策略是key过期之后的策略，是coder自己指定的
 *      淘汰策略是缓存队列满了之后，优先删除哪些key的策略，由使用的框架决定，应该有些是可以配置的
 *
 * 参考https://jishuin.proginn.com/p/763bfbd73355
 * @date 2022 08-01 14:19.
 */
@Slf4j
@Service
public class CaffeineService {
    /**
     * 查找
     * 先查缓存，如果查不到，会查数据库并存入缓存
     *
     * @param id
     */
    @Cacheable(value = "userCache", key = "#id", sync = true)
    public User getUser(long id) {
        //查找数据库
        log.info("从数据库取数据{}", System.currentTimeMillis());
        return new User(id, id + "shit");
    }

    /**
     * 更新/保存
     *
     * @param user
     */
    @CachePut(value = "userCache", key = "#user.id")
    public void saveUser(User user) {
        //todo 保存数据库
        log.info("保存到数据库{}", System.currentTimeMillis());
    }


    /**
     * 删除
     *
     * @param user
     */
    @CacheEvict(value = "userCache", key = "#user.id")
    public void delUser(User user) {
        //todo 保存数据库
        log.info("数据库删除{}", System.currentTimeMillis());
    }
}
