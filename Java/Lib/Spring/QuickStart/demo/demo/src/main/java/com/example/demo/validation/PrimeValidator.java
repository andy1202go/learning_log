/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.validation;

import com.example.demo.validation.entities.PrimeValidateAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liangbo
 * @version V1.0
 * @Title: PrimeValidator.java
 * @Package com.example.demo.validation
 * @Description 质数校验器
 * @date 2022 05-17 15:52.
 */
public class PrimeValidator implements ConstraintValidator<PrimeValidateAnnotation, Integer> {
    private static final String PRIME_PATTERN = "^1?$|^(11+?)\\1+$";


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (null == value) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value; i++) {
            sb.append("1");
        }
        String test = sb.toString();
        Pattern patternNum = Pattern.compile(PRIME_PATTERN);
        Matcher matcher = patternNum.matcher(test);
        boolean find = matcher.find();
        return !find;
    }
}
