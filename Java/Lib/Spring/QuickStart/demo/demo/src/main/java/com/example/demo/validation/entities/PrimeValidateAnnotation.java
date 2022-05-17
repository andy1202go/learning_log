/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation.entities;

import com.example.demo.validation.PrimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liangbo
 * @version V1.0
 * @Title: PrimeValidateAnnotation.java
 * @Package com.example.demo.validation.entities
 * @Description 质数校验注解
 * @date 2022 05-17 15:49.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = {PrimeValidator.class})
public @interface PrimeValidateAnnotation {
    String message() default "非质数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
