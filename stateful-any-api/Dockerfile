FROM arm64v8/openjdk:17-jdk-oracle
COPY build/libs/stateful-any-api-1.0.0.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]