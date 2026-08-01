# -------- build --------
FROM gradle:8.5-jdk21 AS build

WORKDIR /app
COPY libs libs
COPY . voice-chat-service
WORKDIR /app/voice-chat-service
RUN chmod +x gradlew
RUN ./gradlew clean build -x test


# -------- run --------
FROM eclipse-temurin:21-jdk

WORKDIR /app/voice-chat-service

COPY --from=build /app/voice-chat-service/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar", "app.jar"]