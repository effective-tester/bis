FROM gradle:8.12-jdk17 AS build
WORKDIR /backend
COPY . .
RUN gradle bootJar

FROM openjdk:17-jdk-slim
WORKDIR /backend
COPY --from=build /backend/src/main/resources /backend/resources
COPY --from=build /backend/build/libs/*.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]