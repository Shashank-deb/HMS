# --- Stage 1: Build the Application ---
FROM gradle:8.5-jdk17 AS builder

WORKDIR /app

# Copy only configuration files first (for better layer caching)
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Copy source code
COPY src src

# Build the application (skip tests to speed up build in CI/CD)
RUN ./gradlew bootJar -x test --no-daemon

# --- Stage 2: Create the Lightweight Runtime Image ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 1003

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]