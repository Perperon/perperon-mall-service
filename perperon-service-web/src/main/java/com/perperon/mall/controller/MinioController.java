package com.perperon.mall.controller;

import com.perperon.mall.service.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-07-30
 * @apiNote
 */
@RestController
@RequestMapping("/minio")
@Api(tags = "附件上传服务模块")
public class MinioController extends BaseController{

    @Override
    public BaseService getService() {
        return null;
    }
}
