FROM arm64v8/openjdk:17-jdk-oracle
COPY build/libs/stateless-any-api-1.0.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]