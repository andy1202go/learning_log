/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.functional;

import com.example.demo.general.APIException;
import com.example.demo.general.ResultVo;
import com.example.demo.general.SomeRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author liangbo
 * @version V1.0
 * @Title: BuziService.java
 * @Package com.example.demo.functional
 * @Description
 * @date 2022 07-29 17:09.
 */
@Service
public class BuziService {
    @Autowired
    private SomeRpcServiceImpl someRpcService;

    /**
     * Map<String, Function<String, ResultVo>>
     * 也是某种设计模式的实现了
     */
    private Map<String, Function<String, ResultVo<String>>> buziMap = new HashMap<>();

    @PostConstruct
    private void initMap() {
        buziMap.put("do", param -> someRpcService.doSomething(param));
        buziMap.put("query", param -> someRpcService.queryByStringParam(param));
    }

    public ResultVo<String> doBusi(String param) {
        return Optional.ofNullable(buziMap.get(param)).map(f -> f.apply(param)).orElseThrow(() -> new APIException("no this command"));
    }
}
