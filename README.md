# Perperon-Mall-Service

## 项目介绍
Perperon-Mall-Service是一个基于Spring Cloud Alibaba实现的微服务电商系统，采用了现代化的微服务架构，提供了完整的电商业务功能和安全认证机制。

## 技术栈

### 核心框架
- Spring Boot 2.7.6
- Spring Cloud 2021.0.5
- Spring Cloud Alibaba 2021.0.5.0

### 持久层
- MyBatis 3.5.9
- MySQL 8.0.29
- Druid 1.2.9 (数据库连接池)

### 安全框架
- Spring Security
- JWT (JSON Web Token) 0.9.1
- Redis (用于Token管理)

### 工具库
- Hutool 5.8.0 (Java工具包)
- Fastjson 1.2.62 (JSON处理)
- PageHelper 5.3.0 (分页插件)
- Knife4j 3.0.3 (API文档)

### 其他
- Lombok (简化代码)
- Logstash (日志收集)
- ZXing 3.5.1 (二维码处理)

## 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

## 快速开始

### 1. 克隆项目
```bash
git clone <项目地址>
cd perperon-mall-service
```

### 2. 数据库配置
1. 创建数据库：`perperon_mall`
2. 执行SQL脚本：`sql/perperon_mall.sql`

### 3. 修改配置文件
根据实际情况修改以下配置文件：

- `perperon-service-web/src/main/resources/application.yml`：
  - 数据库连接信息
  - Redis连接信息
  - 文件上传路径

- `perperon-service-gateway/src/main/resources/application.yml`：
  - 网关端口和路由配置

### 4. 启动项目

使用Maven命令启动各模块：

```bash
# 安装依赖
mvn clean install

# 启动网关服务
cd perperon-service-gateway
mvn spring-boot:run

# 启动Web服务
cd ../perperon-service-web
mvn spring-boot:run

# 其他模块根据需要启动
```

## 项目结构

```
perperon-mall-service/
├── perperon-common/          # 通用模块
├── perperon-monitor/         # 监控模块
├── perperon-pojo/            # 实体类模块
├── perperon-security/        # 安全模块
├── perperon-service-gateway/ # 网关服务
├── perperon-service-web/     # 业务Web服务
├── perperon-test/            # 测试模块
├── sql/                      # 数据库脚本
└── pom.xml                   # 父项目POM
```

### 模块说明

1. **perperon-common**：通用工具类、常量、异常处理等
2. **perperon-monitor**：系统监控和日志模块
3. **perperon-pojo**：所有实体类定义
4. **perperon-security**：安全认证相关代码
5. **perperon-service-gateway**：API网关，负责路由转发和请求过滤
6. **perperon-service-web**：主要业务逻辑实现

## 核心功能

### 1. 认证授权
- 基于JWT的无状态认证
- 自定义登录接口实现
- 角色权限管理
- 白名单路径配置

### 2. 网关服务
- 动态路由配置
- 请求过滤和转发
- 服务发现与负载均衡

### 3. 业务功能
- 用户管理
- 商品管理
- 订单管理
- 购物车功能
- 文件上传下载

### 4. 系统监控
- Spring Boot Admin监控
- 健康检查
- 日志收集

## 配置说明

### JWT配置
```yaml
jwt:
  tokenHeader: Authorization # JWT存储的请求头
  secret: perperon-service-web # JWT加解密密钥
  expiration: 604800 # JWT过期时间(秒)
  tokenHead: 'Bearer ' # JWT前缀
```

### Redis配置
```yaml
redis:
  database: perperon
  key:
    admin: 'web:admin'
    token: 'web:jwt'
  expire:
    common: 86400 # 过期时间(秒)
```

### 分页配置
```yaml
pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  offsetAsPageNum: true
  rowBoundsWithCount: true
```

### 白名单配置
```yaml
secure:
  ignore:
    urls:
      - "/doc.html"
      - "/swagger-resources/**"
      - "/v2/api-docs"
      - "/actuator/**"
      # 静态资源
      - "/*/*.js"
      - "/*/*.css"
      - "/*/*.jpg"
```

## API文档

系统集成了Knife4j作为API文档工具，启动服务后可访问：

```
http://localhost:8088/doc.html
```

## 开发指南

### 代码规范
- 遵循Java编码规范
- 使用Lombok简化代码
- 接口返回统一格式

### 新增业务模块
1. 在`perperon-pojo`中定义实体类
2. 在`perperon-service-web`中实现业务逻辑
3. 配置MyBatis映射文件
4. 编写API接口
5. 更新Swagger文档

### 安全认证
- 所有需要认证的接口需添加`@PreAuthorize`注解
- 白名单路径在`secure.ignore.urls`中配置
- Token有效期可在JWT配置中调整

## 部署说明

1. 打包项目：`mvn clean package`
2. 部署各模块：
   - 网关服务：`java -jar perperon-service-gateway/target/perperon-service-gateway-1.0-SNAPSHOT.jar`
   - Web服务：`java -jar perperon-service-web/target/perperon-service-web-1.0-SNAPSHOT.jar`

## 联系方式

如有问题或建议，请联系：

- 项目维护者：[维护者名称]
- 邮箱：[邮箱地址]
- 文档地址：[文档链接]

## 许可证

[MIT License](LICENSE)
