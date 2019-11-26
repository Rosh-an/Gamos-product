#FROM alpine:latest
#RUN apk --no-cache add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
#WORKDIR usr/src
#COPY ./target/profile-service-0.0.1-SNAPSHOT.jar /usr/src/profile-service-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/usr/src/profile-service-0.0.1-SNAPSHOT.jar"]
FROM adoptopenjdk/openjdk11-openj9:alpine-jre
WORKDIR usr/src
COPY ./target/profile-service-0.0.1-SNAPSHOT.jar /usr/src/profile-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/profile-service-0.0.1-SNAPSHOT.jar"]
