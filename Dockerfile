FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/users-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]