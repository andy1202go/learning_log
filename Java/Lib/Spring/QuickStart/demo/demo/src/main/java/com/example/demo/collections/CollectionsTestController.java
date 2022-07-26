/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.collections;

import com.alibaba.fastjson.JSON;
import com.example.demo.general.NotControllerResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author liangbo
 * @version V1.0
 * @Title: TestController.java
 * @Package com.example.demo.polymorphism
 * @Description
 * @date 2022 05-27 16:12.
 */
@RestController
@RequestMapping("/collections")
@Valid
@Slf4j
public class CollectionsTestController {

    @RequestMapping(value = "")
    @NotControllerResponseAdvice
    public String test() {
        testEmptySet();
        return "success";
    }

    private static final Map<String, String> MAP = new HashMap<>();

    private void testEmptySet() {
        Set<String> keys = MAP.keySet();

        for (String key : keys) {
            log.info("key={}", key);
        }
    }

    @RequestMapping(value = "/self")
    @NotControllerResponseAdvice
    public String self() {
        SelfCollection selfCollection = new SelfCollection();
        selfCollection.add("shit");
        log.info("{}", JSON.toJSONString(selfCollection));
        return "success";
    }


}
