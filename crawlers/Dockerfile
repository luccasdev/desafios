FROM openjdk:11-jdk

LABEL maintainer="Lucas Souza <lucasrti@hotmail.com>"

VOLUME /tmp

COPY target/crawler-*.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
