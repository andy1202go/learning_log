/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.general;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangbo
 * @version V1.0
 * @Title: Page.java
 * @Package com.example.demo.general
 * @Description 分页信息
 * @date 2022 08-04 9:36.
 */
@Data
public class Page implements Serializable {
    private static final long serialVersionUID = -8666631328869467030L;

    private int currentPage;

    private int totalPage;

    private int pageSize;

    private int totalRecord;

    private static final int CURRENT_PAGE_DEFAULT = 0;

    public Page(final int currentPage, final int totalRecord, final int pageSize) {
        this.totalRecord = totalRecord;
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }

        this.totalPage = calculateTotalPage(pageSize, totalRecord);
        this.currentPage = calculateCurrentPage(currentPage, this.totalPage);
    }

    private int calculateTotalPage(final int pageSize, final int totalRecord) {
        if (totalRecord % pageSize != 0) {
            return totalRecord / pageSize + 1;
        } else {
            return totalRecord / pageSize;
        }
    }

    private int calculateCurrentPage(final int currentPage, final int totalPage) {
        if (currentPage > totalPage && totalPage != 0) {
            return totalPage;
        } else if (currentPage < 1) {
            return CURRENT_PAGE_DEFAULT;
        } else {
            return currentPage;
        }
    }
}
