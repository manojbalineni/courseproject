# satrt with a base image conating Java Runtime
FROM openjdk:17-jdk-slim
LABEL "org.opencontainers.image.authors"="Manoj"
COPY target/accounts.jar accounts.jar
ENTRYPOINT ["java", "-jar" ,"accounts.jar"]

