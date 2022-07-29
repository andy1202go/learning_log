/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.mybatis.model;

import lombok.Data;

/**
 * @author liangbo
 * @version V1.0
 * @Title: City.java
 * @Package com.example.demo.mybatis.model
 * @Description
 * @date 2022 07-29 11:03.
 */
@Data
public class City {
    private Long id;

    private String name;

    private String state;

    private String country;
}
