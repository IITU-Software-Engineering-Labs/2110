osuplayers
# Build Stage: Use a Maven image with JDK 21 to compile and package the application
FROM maven:3.9.0-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only the pom.xml to download dependencies (enabling build caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code into the build container
COPY src ./src

# Package the application (skip tests to speed up the build)
RUN mvn clean package -DskipTests

# Run Stage: Use a lightweight OpenJDK 21 runtime image for the final container
FROM openjdk:21-slim
WORKDIR /app

# Copy the packaged JAR from the build stage into the runtime image
COPY --from=build /app/target/docker-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application

# Use an official OpenJDK 21 runtime as the base image
FROM openjdk:21-slim

# Copy the Spring Boot JAR file from the host to the container and rename it to app.jar
COPY target/docker-demo-0.0.1-SNAPSHOT.jar app.jar

# Declare that the container listens on port 8080
EXPOSE 8080

# Define the command to run the application
main
ENTRYPOINT ["java", "-jar", "app.jar"]