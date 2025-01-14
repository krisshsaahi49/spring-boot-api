# Stage 1: Build Stage
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire project to the working directory
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Run Stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
