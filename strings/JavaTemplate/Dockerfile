FROM maven:3.6.3-jdk-11
LABEL maintainer="Lucas Souza <lucasrti@hotmail.com>"

COPY . .
RUN mvn clean install
WORKDIR target

ENTRYPOINT ["java", "-jar", "StringFormatter-jar-with-dependencies.jar"]