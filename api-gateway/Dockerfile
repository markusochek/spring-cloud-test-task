FROM gradle:7.6-jdk17 AS build
WORKDIR /home/gradle/src
COPY build.gradle.kts settings.gradle.kts /home/gradle/src/
RUN gradle --parallel --build-cache --configure-on-demand dependencies

COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon

FROM openjdk:17-slim
EXPOSE 8765
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]