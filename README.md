# Todo List 后端项目

## 项目介绍
这是一个基于Spring Boot的Todo List后端项目，提供了一套完整的待办事项管理API，支持待办事项的增删改查、状态更新、分页查询等功能。

## 技术栈
- **Spring Boot 3.1.5**: 使用最新的Spring Boot框架，提供强大的依赖管理和自动配置功能
- **MyBatis 3.0.2**: 优秀的持久层框架，采用XML配置方式，实现灵活的SQL映射
- **MySQL**: 数据库存储，使用MySQL驱动版本与Spring Boot 3.x兼容
- **Maven**: 项目构建工具，管理项目依赖
- **Lombok**: 通过注解简化实体类的开发
- **Spring Validation**: 请求参数校验

## 主要功能
- 待办事项的CRUD操作
- 批量添加和更新待办事项
- 支持按完成状态筛选
- 分页查询功能
- 数据验证和异常处理

## 项目结构
```
Todo-List-Backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/merak/todolist/
│   │   │       ├── domain/         # 领域模型
│   │   │       │   └── entity/     # 实体类
│   │   │       ├── mapper/         # MyBatis映射接口
│   │   │       ├── service/        # 业务逻辑层
│   │   │       ├── controller/     # 控制器层
│   │   │       └── TodoListApplication.java
│   │   └── resources/
│   │       ├── mapper/             # MyBatis XML映射文件
│   │       └── application.yml     # 应用配置文件
│   └── test/                       # 测试代码目录
├── pom.xml                         # Maven配置文件
└── Todo-List-API-Tests.postman_collection.json  # API测试集合
```

## 配置说明
项目的主要配置在`application.yml`文件中，包括：
- 服务器端口配置
- 数据库连接配置
- MyBatis配置

## 开发环境要求
- JDK 17
- Maven 3.x
- MySQL 8.x

## 快速开始
1. 克隆项目到本地
2. 确保MySQL服务已启动，并创建名为`todo_list`的数据库
3. 修改`application.yml`中的数据库连接信息
4. 执行以下命令启动项目：
   ```bash
   mvn spring-boot:run
   ```
5. 服务将在`http://localhost:8080`启动

## API文档
项目提供了Postman测试集合文件`Todo-List-API-Tests.postman_collection.json`，导入后可以查看和测试所有API接口。

主要接口包括：
- `GET /api/todos`: 获取待办事项列表（支持分页和状态筛选）
- `POST /api/todos`: 创建新的待办事项
- `PUT /api/todos/{id}`: 更新待办事项状态
- `DELETE /api/todos/{id}`: 删除待办事项
