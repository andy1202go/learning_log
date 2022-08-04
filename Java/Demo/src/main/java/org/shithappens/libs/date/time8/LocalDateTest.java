/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.date.time8;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author liangbo
 * @version V1.0
 * @Title: LocalDateTest.java
 * @Package org.shithappens.libs.date.time8
 * @Description LocalDate
 * <p>
 * Java8新的java.time包
 * ![](https://img-blog.csdnimg.cn/img_convert/ff69f4b9c32dcfabf6157a4f8677ff02.jpeg)
 * @date 2022 08-04 14:26.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        //示例1:Java 8中获取今天的日期
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期:" + today);

        //示例2:Java 8中获取年、月、日信息
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);

        //示例3:Java 8中处理特定日期
        LocalDate spec = LocalDate.of(2022, 8, 4);
        System.out.println("自定义日期:" + spec);

        //示例4:Java 8中判断两个日期是否相等
        if (today.isEqual(spec)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }

        //示例5:Java 8中检查像生日这种周期性事件——比较月和日
        MonthDay birthday = MonthDay.of(spec.getMonth(), spec.getDayOfMonth());
        MonthDay monthDayNow = MonthDay.now();
        if (birthday.equals(monthDayNow)) {
            System.out.println("生日到了");
        } else {
            System.out.println("生日没到");
        }

        //示例6:Java 8中获取当前时间
        LocalDateTime nowTimeFull = LocalDateTime.now();
        LocalTime nowTime = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:" + nowTime);
        System.out.println("获取当前的时间:" + nowTimeFull);

        //示例7：三个小时后的时间为
        System.out.println("三个小时后的时间为:" + nowTime.plusHours(3));

        //示例8:Java 8如何计算一周后的日期
        System.out.println("一周后的日期为:" + today.plusWeeks(1));

        //示例9:Java 8计算一年前或一年后的日期
        System.out.println("一年后的日期:" + today.plusYears(1));

        //示例10:Java 8的Clock时钟类
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());

        //示例11:如何用Java判断日期是早于还是晚于另一个日期
        if (today.isBefore(spec)) {
            System.out.println("目前早于：" + spec);
        } else if (today.isAfter(spec)){
            System.out.println("目前晚于：" + spec);
        }

        //示例12:Java 8中处理时区
        /**
         * @see sun.util.calendar.ZoneInfoFile
         */
//        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        ZoneId america = ZoneId.of("America/Argentina/Buenos_Aires");
        System.out.println("Current date and time in  " + america + " is " + nowTimeFull.atZone(america));

        //示例13:如何表示信用卡到期这类固定日期，答案就在YearMonth
        YearMonth yearMonth = YearMonth.now();
        System.out.println("current 年月:" + yearMonth);

        //示例14:如何在Java 8中检查闰年
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("This is not a Leap year");
        }

        //示例15:计算两个日期之间的天数和月数
        System.out.println("Months left between today and  " + spec + " is " + today.until(spec).getMonths());

        //示例16:在Java 8中获取当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

        //示例17:Java 8中如何使用预定义的格式化工具去解析或格式化日期
        /**
         * @see DateTimeFormatter
         */
        String daysToFormat = "20220304";
        LocalDate formatted = LocalDate.parse(daysToFormat, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(daysToFormat + "  格式化后的日期为:  " + formatted);

        //示例18:字符串互转日期类型
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String str = nowTimeFull.format(format1);
        System.out.println("日期转换为字符串:" + str);
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str, format1);
        System.out.println("日期类型:" + date2);

    }


}
