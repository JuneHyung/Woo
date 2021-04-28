FROM openjdk:8-jdk-alpine
VOLUME /home/ubuntu/backend
ADD ./target/<pom.xml-artifactID>-0.0.1-SNAPSHOP.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "/app.jar"]