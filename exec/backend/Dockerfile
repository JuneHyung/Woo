FROM openjdk:8-jdk-alpine
VOLUME /home/ubuntu/backend
ADD ./target/backend-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "/app.jar"]