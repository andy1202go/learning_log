/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation.entities;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ValidationObject.java
 * @Package com.example.demo.validation
 * @Description validation基础类尝试
 * @date 2022 05-17 14:58.
 */
@Data
public class ValidationObject {

    @Min(value = 2, groups = {ValidationGroups.Save.class, ValidationGroups.Update.class})
    @PrimeValidateAnnotation
    private int age;

    @Valid
    private DeeperObject deeperObject;

    @Size(min = 1)
    private List<@NotBlank String> strs;

}
