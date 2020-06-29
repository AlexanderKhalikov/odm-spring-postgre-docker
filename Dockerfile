FROM openjdk:8-jdk-alpine
MAINTAINER Aleksandr Khalikov aleksandr.khalikov@ibm.com
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar odmIntegrationDocker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/odmIntegrationDocker.jar"]