# # Use official OpenJDK image
# FROM openjdk:17-jdk-slim
#
# # Set working directory
# WORKDIR /app
#
# # Copy pom.xml and source code
# COPY pom.xml .
# COPY src ./src
#
# # Build the application
# RUN apt-get update && apt-get install -y maven \
#     && mvn clean package -DskipTests \
#     && apt-get remove -y maven \
#     && rm -rf /var/lib/apt/lists/*
#
# # Run the JAR
# # ENTRYPOINT ["java","-jar","target/MyPortpolioBackend-0.0.1-SNAPSHOT.jar"]
# ENTRYPOINT ["java", "-Djava.io.tmpdir=/tmp", "-jar", "target/MyPortpolioBackend-0.0.1-SNAPSHOT.jar"]
#
# # -----------------------
# # Stage 1: Build the app
# # -----------------------
# FROM maven:3.9.2-eclipse-temurin-17 AS build
#
# # Set working directory
# WORKDIR /app
#
# # Copy pom.xml first to leverage Docker cache
# COPY pom.xml .
#
# # Download dependencies only
# RUN mvn dependency:go-offline -B
#
# # Copy source code
# COPY src ./src
#
# # Build the application without tests
# RUN mvn clean package -DskipTests
#
# # -----------------------
# # Stage 2: Run the app
# # -----------------------
# FROM openjdk:17-jdk-slim
#
# # Set working directory
# WORKDIR /app
#
# # Copy JAR from build stage
# COPY --from=build /app/target/MyPortpolioBackend-0.0.1-SNAPSHOT.jar app.jar
#
# # Make /tmp writable for Tomcat
# ENV JAVA_OPTS="-Djava.io.tmpdir=/tmp"
#
# # Run the app, using Render's PORT environment variable automatically
# ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]


# # -----------------------
# # Stage 1: Build the app
# # -----------------------
# FROM maven:3.9.2-eclipse-temurin-17 AS build
#
# # Set working directory
# WORKDIR /app
#
# # Copy pom.xml first to leverage Docker cache
# COPY pom.xml ./
#
# # Download dependencies only
# RUN mvn dependency:go-offline -B
#
# # Copy source code
# COPY src ./src
#
# # Build the application without tests
# RUN mvn clean package -DskipTests
#
# # -----------------------
# # Stage 2: Run the app
# # -----------------------
# FROM openjdk:17-jdk-slim
#
# # Set working directory
# WORKDIR /app
#
# # Copy JAR from build stage
# COPY --from=build /app/target/MyPortpolioBackend-0.0.1-SNAPSHOT.jar app.jar
#
# # Make /tmp writable for Tomcat
# ENV JAVA_OPTS="-Djava.io.tmpdir=/tmp"
#
# # Run the app, Railway injects PORT & DB env variables automatically
# ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# -----------------------
# Stage 1: Build the app
# -----------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# -----------------------
# Stage 2: Run the app
# -----------------------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Set temp directory for Tomcat
ENV JAVA_OPTS="-Djava.io.tmpdir=/tmp"

# Railway injects PORT and DB env vars automatically
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
