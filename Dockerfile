FROM openjdk:11
VOLUME /tmp
WORKDIR /app
COPY target/gestione-degli-utenti-*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=docker -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
