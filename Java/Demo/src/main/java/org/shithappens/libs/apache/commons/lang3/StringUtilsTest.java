/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.apache.commons.lang3;


import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

import static org.shithappens.libs.TestingSet.FILE_NAME;
import static org.shithappens.libs.TestingSet.NORMAL_LIST;

/**
 * @author liangbo
 * @version V1.0
 * @Title: StringUtilsTest.java
 * @Package org.shithappens.libs.apache.commons.lang3
 * @Description
 * @date 2022 03-03 15:23.
 */
public class StringUtilsTest {
    static Logger log = Logger.getLogger("StringUtilsTest");

    public static void main(String[] args) {
        joinTest();

    }

    private static void joinTest() {
        log.info("null situation is :" + "\n" + StringUtils.join(null));
        log.info("with index is like :" + "\n" + StringUtils.join(NORMAL_LIST, ":", 0, 3));
        log.info("join with null separator:" + "\n" + StringUtils.join(NORMAL_LIST, null));
        log.info("join with null separator function:" + "\n" + StringUtils.join(NORMAL_LIST));
        log.info("join with joinWith" + "\n" + StringUtils.joinWith(";", NORMAL_LIST, FILE_NAME));
    }
}
