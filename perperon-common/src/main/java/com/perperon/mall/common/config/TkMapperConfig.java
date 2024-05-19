package com.perperon.mall.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @author perperon
 * @date 2024-04-16
 * @apiNote tk.mybatis配置，实现数据ID自定义生成配置
 */
@Configuration
public class TkMapperConfig {

        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            // 创建一个MapperScannerConfigurer对象
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            // 设置扫描的基础包路径
            mapperScannerConfigurer.setBasePackage("com.perperon.mall.mapper");
            // 创建一个Properties对象
            Properties properties = new Properties();
            // 设置Mapper接口扫描的类型为tk.mybatis.mapper.common.Mapper
            properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
            // 设置是否对空值进行判断，false表示不对空值进行判断
            properties.setProperty("notEmpty", "false");
            // 设置扫描顺序为BEFORE，即在MyBatis初始化之前进行扫描
            properties.setProperty("ORDER", "BEFORE");
            // 将properties设置到mapperScannerConfigurer对象中
            mapperScannerConfigurer.setProperties(properties);
            // 返回mapperScannerConfigurer对象
            return mapperScannerConfigurer;
        }

}
