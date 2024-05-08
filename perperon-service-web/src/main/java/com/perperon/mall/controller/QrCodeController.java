package com.perperon.mall.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.server.HttpServerResponse;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author dupengcheng
 * @date 2024-05-08
 * @apiNote  二维码生成
 */
@RestController
@RequestMapping("/qrCode")
public class QrCodeController {

    //下载二维码的 base64 字符串
    @GetMapping("/download1")
    public String download1(HttpServletResponse response) throws Exception {
        //生成默认的白底黑码，中间无logo的二维码
        byte[] pngArr = QrCodeUtil.generatePng("https://www.cnblogs.com/studyjobs",
                300,300);
        String base64 = Base64Utils.encodeToString(pngArr);
        return base64;
    }

    //下载二维码图片
    @GetMapping("/download2")
    public void download2(HttpServletResponse response) throws Exception {

        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(1);
        // 设置前景色，二维码颜色（绿色）
        config.setForeColor(new Color(15, 24, 24, 50));
        // 设置背景色（淡黄色）
        config.setBackColor(new Color(246, 246, 243, 255));
        // 设置中间的 logo 图片
        String logoPath = ResourceUtils.getFile("classpath:static/images/logo.jpg").getPath();
        config.setImg(logoPath);
        // 设置容错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        byte[] pngArr = QrCodeUtil.generatePng("https://hutool.cn/", config);

        //下载文件的响应类型，这里统一设置成了文件流
        //你可以根据自己所提供下载的文件类型，使用不同的响应 mime 类型
        response.setContentType("application/octet-stream;charset=utf-8");
        //设置下载弹出框中默认显示的文件名称，如果指定中文名称的话，需要转成 iso8859-1 编码，解决乱码问题
        String fileName = new String("二维码图片.png".getBytes(), "iso8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.getOutputStream().write(pngArr, 0, pngArr.length);
    }

    //浏览器直接显示二维码图片
    @GetMapping("/download3")
    public void download3(HttpServletResponse response) throws Exception {

        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(1);
        // 设置前景色，二维码颜色（绿色）
        config.setForeColor(new Color(11, 14, 14, 50));
        // 设置背景色（淡黄色）
        config.setBackColor(new Color(253, 252, 252));
        // 设置中间的 logo 图片
        String logoPath = ResourceUtils.getFile("classpath:static/images/logo.jpg").getPath();
        config.setImg(logoPath);
        // 设置容错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        byte[] pngArr = QrCodeUtil.generatePng("https://hutool.cn/", config);

        response.setContentType("image/png");
        response.getOutputStream().write(pngArr, 0, pngArr.length);
    }
}
