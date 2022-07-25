/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.business.nullPointer;

/**
 * @author liangbo
 * @version V1.0
 * @Title: NullPointerDeal.java
 * @Package org.shithappens.libs.business.nullPointer
 * @Description 空指针处理原则
 * https://mp.weixin.qq.com/s/nY6wJuNmgwKcWzLXpIv0bQ
 * @date 2022 07-25 10:06.
 */
public class NullPointerDeal {
    /**
     * null 的困扰
     *      代码不稳定：可能获得null
     *      代码冗余：各种判空代码
     *      含义暧昧不明：null到底是正常还是有问题？null是不是业务可以接受的？
     */

    /**
     * null 替代策略
     *      使用抛异常处理策略：使用抛异常，明确要求调用端进行处理
     *              代码层面处理异常，需要调用端强制干预
     *              入参错误，业务必须数据未获取到
     *              无数据会影响业务的正确处理
     *       使用Optional返回值替代：避免调用端踩坑
     *              获取不到数据是正常的业务场景
     */

    /**
     * 回归Optional的本来的可选的含义，明白告诉调用方，该返回值是可选的
     * 对于Optional的处理也要注意
     */
}
