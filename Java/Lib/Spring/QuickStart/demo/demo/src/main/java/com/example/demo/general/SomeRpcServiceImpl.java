/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.general;

import org.springframework.stereotype.Service;

/**
 * @author liangbo
 * @version V1.0
 * @Title: SomeRpcServiceImpl.java
 * @Package com.example.demo.functional
 * @Description
 * @date 2022 07-29 17:07.
 */
@Service
public class SomeRpcServiceImpl implements RpcService {
    @Override
    public ResultVo<String> queryByStringParam(String param) {
        return new ResultVo("queryByStringParam success " + param);
    }

    @Override
    public ResultVo<String> doSomething(String param) {
        return new ResultVo("doSomething success " + param);
    }
}
