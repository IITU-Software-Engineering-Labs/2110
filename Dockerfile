# Use an official OpenJDK 21 runtime as the base image
FROM openjdk:21-slim

# Copy the Spring Boot JAR file from the host to the container and rename it to app.jar
COPY target/docker-ansar-0.0.1-SNAPSHOT.jar app.jar

# Declare that the container listens on port 8080
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]