/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation.entities;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ValidationList.java
 * @Package com.example.demo.validation.entities
 * @Description 校验泛型集合类
 * @date 2022 05-17 15:47.
 */
@Data
public class ValidationList<E> {
    @Valid
    private List<E> list;
}
