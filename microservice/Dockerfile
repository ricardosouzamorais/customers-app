# FROM openjdk:8-jdk-alpine
FROM openjdk:11-jre

LABEL author="Ricardo Souza Morais"
LABEL author.email="ricardo.souza.morais@gmail.com"

ARG JAR_FILE
ENV _JAVA_OPTIONS "-Xms256m -Xmx512m -Djava.awt.headless=true"

RUN \
	apt-get update \ 
		&& apt-get upgrade \
		&& useradd -ms /bin/bash customerapi \
        && mkdir -p /app/input \
        && chown customerapi:customerapi /app -R

USER customerapi

WORKDIR /app

COPY ${JAR_FILE} /usr/share/myservice/myservice.jar

VOLUME ["/app/input"]

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/myservice/myservice.jar"]