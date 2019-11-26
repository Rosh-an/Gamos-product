FROM adoptopenjdk/openjdk11-openj9:alpine-jre

ADD ./target/chat-service-0.0.1-SNAPSHOT.jar /usr/src/chat-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "chat-service-0.0.1-SNAPSHOT.jar"]