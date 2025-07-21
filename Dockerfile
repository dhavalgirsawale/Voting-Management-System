FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy compiled JAR file (make sure it's committed to GitHub)
COPY voting-management-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
