/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation;

import com.example.demo.validation.entities.ValidationObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ValidationTestController.java
 * @Package com.example.demo.validation
 * @Description validationTestController
 * @date 2022 05-17 15:31.
 */
@RestController
@Valid
@RequestMapping("valitest")
public class ValidationTestController {

    /**
     * {
     *     "age":3,
     *     "deeperObject":{
     *         "id":123
     *     },
     *     "strs":["1"]
     * }
     * @param vo
     * @return
     */
    @PostMapping("post")
    public String test(@RequestBody @Validated ValidationObject vo){
        return "OK";
    }

    @GetMapping("get")
    public String testget(@Validated ValidationObject vo){
        return "OK";
    }
}
