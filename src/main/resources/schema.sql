-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS todo_list CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE todo_list;

-- 创建todo表
CREATE TABLE IF NOT EXISTS todo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    value VARCHAR(255) NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 插入一些测试数据
INSERT INTO todo (value, is_completed) VALUES
    ('完成Java作业', false),
    ('阅读Spring文档', false),
    ('学习MyBatis', true),
    ('复习数据库知识', false);