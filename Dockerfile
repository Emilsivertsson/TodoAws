FROM openjdk:17
LABEL maintainer="BankTDDMaven"

ENV RDS_DB_PASSWORD=""
ENV RDS_DB_USERNAME=""

COPY /target/TodoAws-0.0.1-SNAPSHOT.jar todoaws.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","todoaws.jar"]