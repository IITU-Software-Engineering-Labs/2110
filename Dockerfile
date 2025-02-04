# Используем официальный образ OpenJDK 21 в качестве базового
FROM openjdk:23-slim

# Копируем собранный JAR-файл из локальной папки в контейнер
COPY target/docker-demo-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт, который будет использовать контейнер
EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
