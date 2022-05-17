/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.entities;

/**
 * @author liangbo
 * @version V1.0
 * @Title: User.java
 * @Package org.shithappens.libs.entities
 * @Description 用户测试
 * @date 2022 05-16 14:27.
 */
public class User {
    private String name;
    private int age;
    private Integer bigAge;

    public User() {
    }

    public User(String name, int age, Integer bigAge) {
        this.name = name;
        this.age = age;
        this.bigAge = bigAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getBigAge() {
        return bigAge;
    }

    public void setBigAge(Integer bigAge) {
        this.bigAge = bigAge;
    }
}
