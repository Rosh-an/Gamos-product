FROM adoptopenjdk/openjdk11-openj9:alpine-jre
WORKDIR usr/src
COPY ./target/personal-service-0.0.1-SNAPSHOT.jar /usr/src/personal-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/personal-service-0.0.1-SNAPSHOT.jar"]