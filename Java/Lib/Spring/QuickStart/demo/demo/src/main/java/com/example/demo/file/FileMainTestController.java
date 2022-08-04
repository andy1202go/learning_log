/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.file;

import com.example.demo.general.NotControllerResponseAdvice;
import com.example.demo.general.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author liangbo
 * @version V1.0
 * @Title: FileMainTestController.java
 * @Package com.example.demo.file
 * @Description
 * @date 2022 06-23 15:13.
 */
@RestController
@Slf4j
@RequestMapping(value = "file")
public class FileMainTestController {
    /**
     * 基础的文件读写测试
     * Java io提供的Path和Files来实现
     * 涉及
     * - 文件系统（Windows，Linux，Zip）
     * - 绝对路径，相对路径
     *
     * @return {@link String}
     * @throws IOException ioexception
     */
    @RequestMapping(value = "")
    @NotControllerResponseAdvice
    public ResultVo<String> test() throws IOException {
        Path path = Paths.get("D:\\Projects\\Others\\Mine\\learning_log\\Java\\Lib\\Spring\\QuickStart\\demo\\demo\\src\\main\\java\\com\\example\\demo\\file\\test.txt");
        List<String> contents = Files.readAllLines(path);
        return new ResultVo("success");
    }

    /**
     * 测试类路径
     *
     * @return {@link String}
     * @throws IOException ioexception
     */
    @RequestMapping(value = "2")
    @NotControllerResponseAdvice
    public String testClassPath() throws IOException {
//        ClassPathResource
        return "success";
    }
}
