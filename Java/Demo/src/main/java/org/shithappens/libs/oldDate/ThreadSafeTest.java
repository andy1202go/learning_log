package org.shithappens.libs.oldDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 线程安全测试
 * 参考https://mp.weixin.qq.com/s/6j-h9cND1W9ov0YM_kDTdw
 * 根因：    SimpleDateFormat是使用Calendar进行parse和format的，所以这两个都有问题
 * * The {@link Calendar} instance used for calculating the date-time fields
 * * and the instant of time. This field is used for both formatting and
 * * parsing.
 * 解决方案：每次都new一个；线程变量；加锁
 *
 * @author liangbo
 * @date 2022/07/11
 */
public class ThreadSafeTest {
    static Logger log = Logger.getLogger("ThreadSafeTest");

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        /**
         * 问题2 .format()方法的线程安全问题
         */
        // 创建线程池执行任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            // 执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000); // 得到时间对象
                    formatAndPrint(date); // 执行时间格式化
                }
            });
        }
        threadPool.shutdown(); // 线程池执行完任务之后关闭


        /**
         * 问题1 parse()
         * SimpleDateFormat线程不安全，没有保证线程安全(没有加锁)的情况下，禁止使用全局SimpleDateFormat,否则报错 NumberFormatException
         *
         * private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         *
         */
        for (int i = 0; i < 20; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    // 错误写法会导致线程安全问题
                    System.out.println(Thread.currentThread().getName() + "--" + SIMPLE_DATE_FORMAT.parse("2020-06-01 11:35:00"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i);
            thread.start();
        }
    }

    /**
     * 格式化并打印时间
     */
    private static void formatAndPrint(Date date) {
        String result = SIMPLE_DATE_FORMAT.format(date); // 执行格式化
        log.info("时间：" + result); // 打印最终结果
    }

    /**
     * 正确写法1 每次都new
     */
    private SimpleDateFormat getNewSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 正确写法2 synchronized
     */
    private void safeExecute(){
        for (int i = 0; i < 20; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    synchronized (SIMPLE_DATE_FORMAT) {
                        System.out.println(Thread.currentThread().getName() + "--" + SIMPLE_DATE_FORMAT.parse("2020-06-01 11:35:00"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i);
            thread.start();
        }
    }

    /**
     * 正确写法3 使用ThreadLocal 为每个线程创建一个独立变量
     */
    private static final ThreadLocal<DateFormat> SAFE_SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private void safeThreadLocal(){
        for (int i = 0; i < 20; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "--" + SAFE_SIMPLE_DATE_FORMAT.get().parse("2020-06-01 11:35:00"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i);
            thread.start();
        }
    }
}
