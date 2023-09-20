FROM openjdk:17
WORKDIR /app
ADD /target/credit_service_project-0.0.1-SNAPSHOT.jar credit.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "credit.jar"]