FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Grant execution permission explicitly
RUN chmod +x ./build.sh && chmod +x ./votingapp/mvnw

# Run the build script
RUN ./build.sh

# Run the jar
CMD ["java", "-jar", "votingapp/target/voting-0.0.1-SNAPSHOT.jar"]
