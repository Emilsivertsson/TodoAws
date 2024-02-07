FROM openjdk:17
LABEL maintainer="BankTDDMaven"

COPY /target/TodoAws-0.0.1-SNAPSHOT.jar todoaws.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","todoaws.jar"]