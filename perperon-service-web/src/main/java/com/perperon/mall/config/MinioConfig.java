// MinIO 对象存储配置类（暂未启用，因当前无 MinIO 服务）
//
// 如需启用 MinIO，请：
// 1. 取消 pom.xml（父模块与 web 模块）中 io.minio:minio 依赖的注释
// 2. 取消 application.yml 中 minio 配置的注释，并将 enabled 设为 true
// 3. 恢复本类的 MinioClient Bean 配置（原实现见下方注释）
//
// 原实现：
// package com.perperon.mall.config;
//
// import io.minio.MinioClient;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// @ConditionalOnProperty(name = "minio.enabled", havingValue = "true")
// public class MinioConfig {
//
//     @Value("${minio.endpoint}")
//     private String endpoint;
//
//     @Value("${minio.accessKey}")
//     private String accessKey;
//
//     @Value("${minio.secretKey}")
//     private String secretKey;
//
//     @Bean
//     public MinioClient minioClient() {
//         return MinioClient.builder()
//                 .endpoint(endpoint)
//                 .credentials(accessKey, secretKey)
//                 .build();
//     }
// }
