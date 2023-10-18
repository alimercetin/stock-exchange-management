FROM openjdk:17-ea-jdk-oracle
ARG JAR_FILE=build/libs/stock-exchange-management-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]