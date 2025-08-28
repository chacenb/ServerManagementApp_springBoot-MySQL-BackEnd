# --------------------------------------
# DOCKERIZING SPRING BOOT APP | ADVANCED
# --------------------------------------
# 1. Build stage: compile the app in the container
# --------------------------------------
FROM eclipse-temurin:17-jdk-jammy AS build

# Set '/app' the working directory in the container (cd into it)
WORKDIR /app

# Install Maven inside this image so we can build the Spring Boot app
# apt-get update → update package lists
# apt-get install -y maven → install Maven
# rm -rf /var/lib/apt/lists/* → clean up to reduce image size
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copy only the pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .

# Download all Maven dependencies without running tests
# This allows Docker to cache dependencies so they don't need to be downloaded on every build
RUN mvn dependency:go-offline -DskipTests

# Copy the source code into the container
COPY src src

# Build the project and create the JAR inside target/
# The -DskipTests flag skips running unit tests to speed up the build
# After building, rename the resulting JAR to a consistent name "app.jar"
RUN mvn package -DskipTests && mv target/*.jar app.jar


# --------------------------------------
# 2. Runtime stage: run the app
# --------------------------------------
# Uses a slimmer JRE image
FROM eclipse-temurin:17-jre-jammy AS runtime

WORKDIR /app

# Create a non-root user
RUN useradd -u 1001 -m appuser
USER appuser

# Copy only the built jar
COPY --from=build /app/app.jar .

# Expose the application port (adjust based on your app's config)
EXPOSE 8080

# Specifies the command that runs the application when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]

