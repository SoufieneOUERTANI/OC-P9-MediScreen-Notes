FROM openjdk:11
MAINTAINER ouertani.com
ARG JAR_FILE=target/docker-mediscreen-note-1.0.1.jar
COPY ${JAR_FILE} mediscreen-note-1.0.1.jar
#EXPOSE 8081
ENTRYPOINT ["java","-jar","/mediscreen-note-1.0.1.jar"]