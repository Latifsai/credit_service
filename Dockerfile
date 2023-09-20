FROM openjdk:17
ADD /target/credit_service_project-0.0.1-SNAPSHOT.jar credit.jar
ENTRYPOINT ["java", "-jar", "credit.jar"]