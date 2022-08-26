FROM openjdk:17-jdk

VOLUME /tmp

COPY target/xxx-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV spring.profiles.active "dev"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

