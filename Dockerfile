# Stage 1: Build JAR
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /src
COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
COPY src src
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN useradd --system --uid 10001 store
COPY --from=build --chown=store:store /src/build/libs/*.jar /app/store.jar
USER store
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/store.jar"]