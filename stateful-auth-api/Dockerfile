FROM arm64v8/openjdk:17-jdk-oracle
COPY build/libs/stateful-auth-api-1.0.0.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]