FROM openjdk:17
LABEL maintainer="BankTDDMaven"

COPY /target/TodoAws.jar todoaws.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","todoAWS.jar"]