FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/*.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]