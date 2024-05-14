package com.perperon.mall.common.utils;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author perperon
 * @date 2024/5/14
 * @apiNote
 */
public class Help {
    private static final Logger LOGGER = LoggerFactory.getLogger(Help.class);

    private static String db="mysql";

    public static void startPage(Map<String,Object> params) {
        if (!StrUtil.isEmpty(params.get("pageNum").toString())){
            if(params.get("pageSize").toString().equals("0")){
                params.remove("pageNum");
                params.remove("pageSize");
            } else {
                PageHelper.startPage(params);
            }
        }
    }
}
