# Builder stage
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# 1. Copy Maven wrapper files
COPY .mvn/ .mvn
COPY mvnw ./

# 2. MAKE EXECUTABLE (CRITICAL FIX)
RUN chmod +x mvnw

# 3. Proceed with build
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# Runtime stage (unchanged)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]