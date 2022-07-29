/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.mybatis.dao;

import com.example.demo.mybatis.model.City;
import com.example.demo.mybatis.model.Encrypt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 80338398
 * @version V1.0
 * @Title: CityDao.java
 * @Package com.example.demo.mybatis.dao
 * @Description
 * @date 2022 07-29 11:03.
 */
@Mapper
public interface CityDao {
    //使用Encrypt加解密
    @Select("SELECT * FROM CITY WHERE state = #{state}")
    City findByState(@Param("state") Encrypt state);
}
