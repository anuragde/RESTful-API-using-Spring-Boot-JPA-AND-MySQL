FROM hirokimatsumoto/alpine-openjdk-11
EXPOSE 8080
ADD /target/spring-boot-1.0-SNAPSHOT.jar spring-boot-docker-1.0.jar
ENTRYPOINT ["java","-jar","spring-boot-docker-1.0.jar"]
