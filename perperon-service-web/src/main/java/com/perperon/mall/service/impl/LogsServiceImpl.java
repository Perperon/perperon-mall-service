package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.mapper.LogsMapper;
import com.perperon.mall.pojo.Logs;
import com.perperon.mall.service.LogsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class LogsServiceImpl implements LogsService {

    @Resource
    private LogsMapper logsMapper;

    @Override
    public Mapper<Logs> getMapper() {
        return logsMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return null;
    }
}
