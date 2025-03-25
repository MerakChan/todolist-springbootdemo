FROM eclipse-temurin:17-jre

WORKDIR /app

# 设置时区
ENV TZ=Asia/Shanghai

# 从target目录复制jar包
COPY ./target/*.jar app.jar

EXPOSE 8080

# 添加环境变量支持
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=${SPRING_PROFILES_ACTIVE:dev}"]