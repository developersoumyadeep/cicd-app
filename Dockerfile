FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

COPY ./src/main/resources /app/config

WORKDIR /app

COPY ./target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]