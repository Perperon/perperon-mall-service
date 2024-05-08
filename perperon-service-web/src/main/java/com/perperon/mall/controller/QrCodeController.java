package com.perperon.mall.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.server.HttpServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author dupengcheng
 * @date 2024-05-08
 * @apiNote  二维码生成
 */
@RestController
@RequestMapping("/qrCode")
public class QrCodeController {

    @GetMapping("/getQrCode")
    public void getQrCode(HttpServerResponse response) throws IOException {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));
    }
}
