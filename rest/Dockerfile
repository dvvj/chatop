FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

## Add the wait script to the image
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait

## ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/app.jar"]
CMD /wait && java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -jar /app.jar