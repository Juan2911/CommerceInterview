FROM openjdk:19-jdk-alpine
COPY target/interview-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]