FROM ofayau/ejre:8-jre
MAINTAINER Andreas Tell <andreas.tell@callistaenterprise.se>
EXPOSE 8080
COPY libs/docker-aws-spring-demo-0.0.1-SNAPSHOT.jar docker-aws-spring-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Djava.security.egd=file:/dev/urandom", "-jar", "docker-aws-spring-demo-0.0.1-SNAPSHOT.jar"]
