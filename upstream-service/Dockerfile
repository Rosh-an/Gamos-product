FROM adoptopenjdk/openjdk11-openj9:alpine-jre
WORKDIR usr/src
COPY ./target/upstream-service-0.0.1-SNAPSHOT.jar /usr/src/upstream-service-0.0.1-SNAPSHOT.jar
COPY VideoBot.json usr/src/VideoBot.json
ENTRYPOINT ["java","-jar","/usr/src/upstream-service-0.0.1-SNAPSHOT.jar"]