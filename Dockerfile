# Stage 1: build with Gradle (JDK 21)
FROM gradle:9.2-jdk21 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon -x test

# Stage 2: runtime with a JRE 21 image
FROM eclipse-temurin:21-jre-jammy
ARG JAR=/home/gradle/project/build/libs/*.jar
COPY --from=builder ${JAR} /app/app.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","/app/app.jar"]
