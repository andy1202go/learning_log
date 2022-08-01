/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.cache.caffeine;

import com.example.demo.general.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.demo.general.Constant.USER_ID;


/**
 * @author 80338398
 * @date 2022/07/27
 */
@RestController
@Valid
@RequestMapping("cache/caf")
@Slf4j
public class CaffeineTestController {
    @Autowired
    private CaffeineService caffeineService;

    @RequestMapping("")
    public ResultVo testVo(Long id) {
        return new ResultVo(caffeineService.getUser(null == id ? USER_ID : id));
    }

    @RequestMapping("te")
    public ResultVo testExpire(Long id) throws InterruptedException {
        caffeineService.getUser(null == id ? USER_ID : id);
        Thread.sleep(120*1000);
        return new ResultVo(caffeineService.getUser(null == id ? USER_ID : id));
    }

}
