/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.collections;

import lombok.Data;

import java.util.AbstractList;

/**
 * @author liangbo
 * @version V1.0
 * @Title: SelfCollection.java
 * @Package com.example.demo.collections
 * @Description 自定义一个集合类
 *
 * 存在很多问题，需要学习下，List怎么自己实现一个
 * @date 2022 06-22 11:03.
 */
@Data
public class SelfCollection<E> extends AbstractList<E> {
    private E[] data;
    private E[] hasData;

    public SelfCollection() {
        data = (E[]) new Object[100];
    }

    @Override
    public E get(int index) {
        return hasData[index];
    }

    @Override
    public boolean add(E e) {
        int size = size();
        data[size++] = e;
        return true;
    }

    @Override
    public int size() {
        return hasData.length;
    }
}
