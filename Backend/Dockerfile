FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build
ARG JAR_FILE=/target/backend-0.0.1.jar

# cd /opt/app
WORKDIR /opt/app

# cp /target/backend-0.0.1.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]