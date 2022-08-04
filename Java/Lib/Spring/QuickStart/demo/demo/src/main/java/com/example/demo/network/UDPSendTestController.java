/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.network;

import com.example.demo.general.NotControllerResponseAdvice;
import com.example.demo.general.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * 一般测试控制器
 *
 * @author 80338398
 * @date 2022/07/27
 */
@RestController
@Valid
@RequestMapping("net")
@Slf4j
public class UDPSendTestController {

    @PostMapping("")
    @NotControllerResponseAdvice
    public String test() {
        return "OK";
    }

    @GetMapping("udp")
    public ResultVo<String> dnsServer(String host) throws Exception {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            DatagramPacket datagramPacket = new DatagramPacket(host.getBytes(),
                    host.getBytes().length, InetAddress.getByName("127.0.0.1"), 8888);
            datagramSocket.send(datagramPacket);
        }
        return new ResultVo("Send OK");
    }
}
