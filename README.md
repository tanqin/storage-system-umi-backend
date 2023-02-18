# umi 仓库管理系统

## 项目仓库地址

前端：[storage-system-umi-frontend](https://github.com/tanqin/storage-system-umi-frontend)

后端：[storage-system-umi-backend](https://github.com/tanqin/storage-system-umi-backend)

## 前端说明

环境：node v16.17.1

技术：react + umi3 + antd + echarts

### 使用

1. 执行 `yarn` 安装依赖
2. 执行 `yarn start` 运行项目

## 后端说明

环境：jdk8、springboot2.7、mysql5.7、maven3.8

技术：springboot + mybatis-plus + jwt + lombok

### 使用

1. mysql 导入 `sql/storage_system_sql.sql`
2. `src/main/resources/application.yml` 修改「数据库配置」一项中 `url`、`username`、`password` 为自己数据库相应配置
3. 运行 `src/main/java/com/tanqin/StorageSystemUmiBackend.java` 中的 `main` 方法


