FROM eclipse-temurin:11-alpine
EXPOSE 8051
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
