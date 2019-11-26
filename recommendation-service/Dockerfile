FROM adoptopenjdk/openjdk11-openj9:alpine-jre
WORKDIR usr/src
COPY ./target/recommendation-service-0.0.1-SNAPSHOT.jar /usr/src/recommendation-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/recommendation-service-0.0.1-SNAPSHOT.jar"]
