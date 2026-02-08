FROM amazoncorretto:21-alpine
ARG JAR_FILE=target/portfolio-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} portfolio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "portfolio.jar"]