FROM openjdk:17
LABEL maintainer="BankTDDMaven"

COPY /target/TodoAws-0.0.1-SNAPSHOT.jar TodoAws-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","TodoAws-0.0.1-SNAPSHOT.jar"]