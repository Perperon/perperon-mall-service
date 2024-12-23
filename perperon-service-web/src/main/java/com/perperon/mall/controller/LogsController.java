package com.perperon.mall.controller;

import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.LogsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024/12/23
 * @apiNote
 */
@RestController
@RequestMapping("/logs")
@Api(tags = "日志管理")
@RequiredArgsConstructor
public class LogsController extends BaseController {

    private final LogsService logsService;

    @Override
    public BaseService getService() {
        return logsService;
    }
}
