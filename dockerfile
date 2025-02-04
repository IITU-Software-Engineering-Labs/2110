FROM openjdk:23-jdk-slim


# Копировать скомпилированный JAR файл в контейнер
COPY target/untitled-1.0-SNAPSHOT.jar app.jar

# Открыть порт 8080
EXPOSE 8080

# Запустить приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
