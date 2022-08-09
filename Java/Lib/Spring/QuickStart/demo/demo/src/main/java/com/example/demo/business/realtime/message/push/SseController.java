/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.business.realtime.message.push;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liangbo
 * @version V1.0
 * @Title: SseController.java
 * @Package com.example.demo.business.realtime.message.push
 * @Description sse
 * @date 2022 08-09 11:41.
 */
@Controller
@RequestMapping("/sse")
public class SseController {

    /**
     * sse 页面
     *
     */
    @RequestMapping("/index")
    public String sse() {
        return "sse";
    }

    /**
     * sse 订阅消息
     */
    @GetMapping(path = "sub/{id}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @ResponseBody
    public SseEmitter sub(@PathVariable String id) throws IOException {

        return SseEmitterUtils.connect(id);
    }

    /**
     * sse 发布消息
     */
    @GetMapping(path = "push")
    @ResponseBody
    public void push(String id, String content) throws IOException {
        SseEmitterUtils.sendMessage(id, content);
    }

    @ResponseBody
    @GetMapping(path = "breakConnect")
    public void breakConnect(String id, HttpServletRequest request, HttpServletResponse response) {
        request.startAsync();
        SseEmitterUtils.removeUser(id);
    }
}
