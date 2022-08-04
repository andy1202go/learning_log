/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.general;

/**
 * @author 80338398
 * @version V1.0
 * @Title: RpcService.java
 * @Package com.example.demo.general
 * @Description 通用RPC接口
 * @date 2022 07-29 16:46.
 */
public interface RpcService {
    ResultVo<String> queryByStringParam(String param);

    ResultVo<String> doSomething(String param);
}
