/*# Используем официальный образ OpenJDK 21
FROM openjdk:21-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR-файл в контейнер
COPY target/docker-demo-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
//