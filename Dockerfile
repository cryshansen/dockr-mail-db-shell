# Use Java 21 runtime
FROM eclipse-temurin:21-jdk-jammy
VOLUME /tmp
COPY target/dockr-mail-db-shell-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
