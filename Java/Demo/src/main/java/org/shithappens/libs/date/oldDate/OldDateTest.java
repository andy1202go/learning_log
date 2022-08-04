/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.date.oldDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author liangbo
 * @version V1.0
 * @Title: OldDateTest.java
 * @Package org.shithappens.libs.date.oldDate
 * @Description 日期-旧情况test
 * @date 2022 03-09 16:36.
 */
public class OldDateTest {
    static Logger log = Logger.getLogger("OldDateTest");


    public static void main(String[] args){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,Calendar.DECEMBER,31);
        log.info(defaultFormat(calendar.getTime()));
        log.info(format(calendar.getTime(),"YYYY-MM-dd HH:mm:ss"));


    }

    private static String defaultFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    private static String format(Date date,String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}
