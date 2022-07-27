/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.lombok;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * lombok测试控制器
 *
 * @author 80338398
 * @date 2022/07/27
 */
@RestController
@Valid
@RequestMapping("lombok")
public class LombokTestController {

    @PostMapping("/db")
    public DataAndBuilder test() {
        final String d = "d";
        new DataAndBuilder(d);
        return new DataAndBuilder.DataAndBuilderBuilder().msg("desc").build();
    }

    @PostMapping("/db/no")
    public DataAndBuilder testNO() {
        return new DataAndBuilder();
    }

    @PostMapping("/db/arg")
    public DataAndBuilder testArg() {
        final String d = "d";
        return new DataAndBuilder(d);
    }
}
