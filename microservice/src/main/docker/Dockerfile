FROM openjdk:8-jdk-alpine

LABEL author="Ricardo Souza Morais"
LABEL author.email="ricardo.souza.morais@gmail.com"

RUN \
	apk update \ 
		&& apk upgrade \
		&& addgroup -S customerapi \
		&& adduser -S -G customerapi customerapi \
        && mkdir -p /app/input \
        && chown customerapi:customerapi /app -R

# Executing the app
USER customerapi

WORKDIR /app

COPY --chown=customerapi:customerapi ./src/main/docker/ ./
RUN find . -type f -iname "*.sh" -exec chmod +x {} \; 

VOLUME ["/app/input"]

ENTRYPOINT ["/app/run.sh"]