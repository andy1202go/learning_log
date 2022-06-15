/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.polymorphism;

import org.springframework.stereotype.Service;

/**
 * @author liangbo
 * @version V1.0
 * @Title: TwoPolyTestApi.java
 * @Package com.example.demo.polymorphism
 * @Description
 * @date 2022 05-27 16:14.
 */
@Service
public class TwoPolyTestApi implements PolyTestApi {
    @Override
    public String getSth() {
        return "two";
    }
}
