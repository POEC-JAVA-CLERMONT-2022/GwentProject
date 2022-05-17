FROM openjdk:17-jdk-alpine
COPY ./target/gwent_projet-0.0.1-SNAPSHOT.jar gwent_projet-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","gwent_projet-0.0.1-SNAPSHOT.jar"]