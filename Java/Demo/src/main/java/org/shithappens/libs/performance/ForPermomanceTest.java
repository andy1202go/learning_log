/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.performance;

import org.shithappens.libs.TestingSet;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ForPermomanceTest.java
 * @Package org.shithappens.libs.performance
 * @Description for循环的性能关系
 * @date 2022 08-09 10:03.
 */
public class ForPermomanceTest {

    public static void main(String[] args) {
//        test(TestingSet.BIG_INT, TestingSet.SMALL_INT);
        test(TestingSet.SMALL_INT, TestingSet.BIG_INT);
    }

    private static void test(int outerInt, int innerInt) {
        long mid2 = System.currentTimeMillis();
        for (int i = 0; i < outerInt; i++) {
            for (int j = 0; j < innerInt; j++) {
                System.out.println("test" + i + j);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end - mid2));
    }
}
