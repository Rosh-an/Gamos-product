FROM adoptopenjdk/openjdk11-openj9:alpine-jre
WORKDIR usr/src
COPY ./target/graph-com-service-0.0.1-SNAPSHOT.jar /usr/src/graph-com-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/graph-com-service-0.0.1-SNAPSHOT.jar"]