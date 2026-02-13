# ========= Stage 1: Build =========
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# copy pom first (caching dependencies)
COPY pom.xml .

RUN mvn dependency:go-offline

# copy source code
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# ========= Stage 2: Runtime =========
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
