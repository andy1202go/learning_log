/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author liangbo
 * @version V1.0
 * @Title: DataAndBuilder.java
 * @Package com.example.demo.lombok
 * @Description Data和Builder同时使用异常
 *
 * 其实就是@Data和@Builder同时使用，是没有生成无参构造方法的；
 * 有些框架是要求一定要有无参构造的，springboot实验是OK的
 * 但是@NoArgsConstructor和@Builder是不能一起使用的，但又存在这种使用场景
 * 尝试使用@Tolerate
 *
 * @date 2022 07-27 15:06.
 */
@Data
@Builder
public class DataAndBuilder {
    private String msg;
    private String desc;

    @Tolerate
    DataAndBuilder(){
    }

    @Tolerate
    DataAndBuilder(String msg){
        this.msg = msg;
    }
}
