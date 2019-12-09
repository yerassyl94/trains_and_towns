FROM gradle:5.4.0-jdk11 as builder

COPY --chown=gradle:gradle . .

RUN gradle --no-daemon build

FROM openjdk:11-oracle

WORKDIR /app

COPY --from=builder /home/gradle/build/libs/trains_and_towns*.jar /app/trains_and_towns.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/trains_and_towns.jar"]