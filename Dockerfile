# docker build -t blk-hacking-ind-aniruddha-gayake .
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 5477

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=5477"]
